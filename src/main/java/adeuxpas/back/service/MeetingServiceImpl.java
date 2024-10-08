package adeuxpas.back.service;

import adeuxpas.back.dto.mapper.*;
import adeuxpas.back.dto.meeting.MeetingRequestDTO;
import adeuxpas.back.dto.meeting.MeetingResponseDTO;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.Meeting;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AdStatus;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.MeetingRepository;
import jakarta.persistence.EntityNotFoundException;

import adeuxpas.back.repository.UserRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.*;
import java.math.BigDecimal;

/**
 * Implementation class for the MeetingService interface.
 * <p>
 * This service class provides implementations for meeting-related operations.
 * </p>
 * <p>
 * It interacts with the MeetingRepository to perform database operations
 * related to meetings.
 * </p>
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final AdRepository adRepository;
    private final StripePaymentService stripePaymentService;
    private final UserRepository userRepository;
    private static final String MEETING_NOT_FOUND_MESSAGE = "Meeting not found for ad ID: ";

    private static final Logger logger = LoggerFactory.getLogger(MeetingServiceImpl.class);

    /**
     * Constructor for MeetingServiceImpl.
     *
     * @param meetingRepository The MeetingRepository for interacting with
     *                          meeting-related database operations.
     * @param meetingMapper     The MeetingMapper for converting entities to DTOs.
     */

    public MeetingServiceImpl(@Autowired MeetingRepository meetingRepository, @Autowired MeetingMapper meetingMapper,
            @Autowired AdRepository adRepository, @Autowired StripePaymentService stripePaymentService,
            @Autowired UserRepository userRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.adRepository = adRepository;
        this.stripePaymentService = stripePaymentService;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a list of meetings for a specific buyer.
     *
     * @param id The ID of the buyer.
     * @return A list of MeetingDTO objects representing meetings for the buyer,
     *         filtered by INITIALIZED status and future dates.
     */
    @Override
    public List<MeetingResponseDTO> getMeetingsByBuyerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndBuyerIdOrderByDate(MeetingStatus.INITIALIZED, id);
        LocalDateTime now = LocalDateTime.now();
        return meetings.stream()
                .filter(meeting -> meeting.getDate().isAfter(now))
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    /**
     * Retrieves a list of meetings for a specific seller.
     *
     * @param id The ID of the seller.
     * @return A list of MeetingDTO objects representing meetings for the seller,
     *         filtered by INITIALIZED status and future dates.
     */
    @Override
    public List<MeetingResponseDTO> getMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrderByDate(MeetingStatus.INITIALIZED,
                id);
        LocalDateTime now = LocalDateTime.now();
        return meetings.stream()
                .filter(meeting -> meeting.getDate().isAfter(now))
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    /**
     * Retrieves a list of accepted meetings for a specific seller or buyer.
     *
     * @param id The ID of the seller or buyer.
     * @return A list of MeetingDTO objects representing accepted meetings for the
     *         user,
     *         with status updated to PLANNED for past meetings.
     */
    @Override
    public List<MeetingResponseDTO> getAcceptedMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository
                .findByStatusAndSellerIdOrBuyerIdOrderByDate(MeetingStatus.ACCEPTED, id, id);
        LocalDateTime now = LocalDateTime.now();
        return meetings.stream()
                .map(meeting -> {
                    if (meeting.getDate().isBefore(now) && meeting.getStatus() == MeetingStatus.ACCEPTED) {
                        meeting.setStatus(MeetingStatus.TOBEFINALIZED);
                        meetingRepository.save(meeting);
                    }
                    return meetingMapper.meetingToMeetingDTO(meeting);
                })
                .toList();
    }

    /**
     * Retrieves a list of due meetings for a specific user.
     *
     * @param id The ID of the user.
     * @return A list of MeetingDTO objects representing past meetings for the user.
     */
    @Override
    public List<MeetingResponseDTO> getDueMeetings(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        List<Meeting> meetings = meetingRepository.findPastMeetings(id, now.toLocalDateTime());
        return meetings.stream()
                .map(meeting -> {
                    if (meeting.getStatus() == MeetingStatus.ACCEPTED
                            && meeting.getDate().isBefore(now.toLocalDateTime())) {
                        meeting.setStatus(MeetingStatus.TOBEFINALIZED);
                        meetingRepository.save(meeting);
                    }
                    return meetingMapper.meetingToMeetingDTO(meeting);
                })
                .toList();
    }

    /**
     * Accepts a meeting by changing its status to ACCEPTED.
     *
     * @param meetingId The ID of the meeting to accept.
     * @return An Optional containing the updated MeetingDTO if the meeting was
     *         found and accepted,
     *         or an empty Optional if the meeting was not found or couldn't be
     *         accepted.
     */
    @Override
    public Optional<MeetingResponseDTO> acceptMeeting(Long meetingId) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(meetingId);

        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            if (meeting.getStatus() == MeetingStatus.INITIALIZED) {
                meeting.setStatus(MeetingStatus.ACCEPTED);
                Meeting updatedMeeting = meetingRepository.save(meeting);
                return Optional.of(meetingMapper.meetingToMeetingDTO(updatedMeeting));
            }
        }

        return Optional.empty();
    }

    /**
     * Retrieves the alias of the buyer associated with the ad.
     *
     * @param adId The ID of the ad.
     * @return The alias of the buyer.
     * @throws EntityNotFoundException if no meeting is found for the given ad ID.
     */
    @Override
    public String getBuyer(Long adId) {
        Meeting meeting = meetingRepository.findByAdsId(adId)
                .orElseThrow(() -> new EntityNotFoundException(MEETING_NOT_FOUND_MESSAGE + adId));
        return meeting.getBuyer().getAlias();
    }

    /**
     * Retrieves the date of the meeting associated with the ad.
     *
     * @param adId The ID of the ad.
     * @return The date and time of the meeting.
     * @throws EntityNotFoundException if no meeting is found for the given ad ID.
     */
    @Override
    public LocalDateTime getMeetingDate(Long adId) {
        Meeting meeting = meetingRepository.findByAdsId(adId)
                .orElseThrow(() -> new EntityNotFoundException(MEETING_NOT_FOUND_MESSAGE + adId));
        return meeting.getDate();
    }

    /**
     * Initializes a new meeting based on the provided ProposedMeetingRequestDTO.
     * Maps the DTO to a Meeting entity, associates the corresponding ad if present,
     * sets the status to INITIALIZED,
     * saves the meeting entity, and returns the ID of the saved meeting.
     *
     * @param meetingRequestDTO the DTO containing the details of the proposed
     *                          meeting
     * @return the ID of the initialized and saved meeting
     */
    @Override
    public Long initializeMeeting(MeetingRequestDTO meetingRequestDTO) {
        Meeting meeting = meetingMapper.proposedMeetingRequestDTOToMeeting(meetingRequestDTO);

        Optional<Ad> optionalAd = adRepository.findById(meetingRequestDTO.adId);
        if (optionalAd.isPresent()) {
            Ad ad = optionalAd.get();
            Set<Ad> ads = new HashSet<>();
            ads.add(ad);
            meeting.setAds(ads);
            ad.setStatus(AdStatus.RESERVED);
            adRepository.save(ad);
        }
        meeting.setStatus(MeetingStatus.INITIALIZED);

        Meeting savedMeeting = meetingRepository.save(meeting);
        return savedMeeting.getIdMeeting();
    }

    /**
     * Finalizes a meeting 
     * Checks if the meeting is already present in the DB, then implements the part
     * of the finalization logic
     * that handles payment via the Stripe API
     * 
     * @param meetingId the ID of the meeting to be finalized
     * @param userId In order to check if the user executing the request is the current ad's buyer or seller
     */
    @Override
    public MeetingResponseDTO finalizeMeeting(Long meetingId, Long userId) {
        Optional<Meeting> meetingToBeFinalized = this.meetingRepository.findById(meetingId);
        if (meetingToBeFinalized.isPresent()) {
            Optional<User> seller = this.userRepository.findById(meetingToBeFinalized.get().getSeller().getId());
            if (seller.isPresent()) {
                // Mocked the seller's info and created a Stripe Connect Account for the seller,
                // which is needed
                // in order to attach the seller's profile info, including his bank account, to
                // our Stripe account,
                // and make the payout to this bank account, once the funds have been captured
                // from the buyer's card
                String line1 = "123 Main St";
                String dobDay = "01";
                String dobMonth = "01";
                String dobYear = "1990";
                String firstName = "John";
                String lastName = "Doe";
                String sellerEmail = seller.get().getEmail();
                String sellerCity = seller.get().getCity();
                String sellerPostalCode = seller.get().getPostalCode();
                String businessProfileUrl = "https://5390-82-76-31-95.ngrok-free.app";
                String stripePaymentIntentId = meetingToBeFinalized.get().getStripePaymentIntentId();
                BigDecimal amount = meetingToBeFinalized.get().getAds().stream().findFirst().get().getPrice();
                // the seller's tokenized bank account, which we've saved to our DB at user
                // registration
                String bankAccountToken = meetingToBeFinalized.get().getSeller().getBankAccountTokenId();
                if (stripePaymentIntentId != null) {
                    // Create the seller's Connect Account, capture the buyer's funds, and make the
                    // payout to the seller's bank account:
                    try {
                        // Create an account token with the required info
                        Token accountToken = stripePaymentService.createAccountToken(sellerEmail, sellerCity, line1,
                                sellerPostalCode, dobDay, dobMonth, dobYear, firstName, lastName);
                        // Create a Stripe Connect account for the seller using the account token
                        Account account = stripePaymentService.createConnectAccountWithToken(accountToken.getId(),
                                businessProfileUrl);
                        // Add external bank account to the Stripe Connect account using the bank
                        // account token
                        ExternalAccount externalAccount = stripePaymentService
                                .addExternalBankAccountToStripeConnect(account.getId(), bankAccountToken, amount);
                        // Capture the payment
                        this.stripePaymentService.capturePayment(stripePaymentIntentId);
                        // Create a payout to the external account (and its associated bank account)
                        stripePaymentService.createPayout(account.getId(), amount.longValue() * 100, "eur",
                                externalAccount.getId());
                    } catch (StripeException stripeException) {
                        logger.error("Error capturing payment or creating payout: {}", stripeException.getMessage());
                    }
                }
            }
        }
        return this.finalizeSell(meetingToBeFinalized, userId);
    }

    /**
     * This method checks if the meeting is ready to pass from the 'to be finalized' status to the 'finalized' one 
     * in which case the ad related to the meeting can have its status set to SOLD
     * 
     * @param meetingToBeFinalized
     * @param userId
     * @return
     */
    private MeetingResponseDTO finalizeSell(Optional<Meeting> meetingToBeFinalized, Long userId) {
        if (meetingToBeFinalized.isEmpty()) {
            throw new IllegalArgumentException("Meeting not found.");
        }
        Meeting meeting = meetingToBeFinalized.get();
        if (userId.equals(meeting.getBuyer().getId())) {
            meeting.setValidatedByBuyer(true);
        } else {
            meeting.setValidatedBySeller(true);
        }
        Meeting updatedMeeting = meetingRepository.save(meeting);
        if (meeting.getStatus() == MeetingStatus.TOBEFINALIZED
                && Boolean.TRUE.equals(meeting.getValidatedByBuyer())
                && Boolean.TRUE.equals(meeting.getValidatedBySeller())) {
            meeting.setStatus(MeetingStatus.FINALIZED);
            meeting.getAds().iterator().next().setStatus(AdStatus.SOLD);
            Meeting finalizedMeeting = meetingRepository.save(meeting);
            return meetingMapper.meetingToMeetingDTO(finalizedMeeting);
        }
        return meetingMapper.meetingToMeetingDTO(updatedMeeting);
    }
}
