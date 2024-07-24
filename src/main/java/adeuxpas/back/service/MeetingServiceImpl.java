package adeuxpas.back.service;

import adeuxpas.back.dto.*;
import adeuxpas.back.dto.mapper.*;
import adeuxpas.back.entity.Ad;
import adeuxpas.back.entity.Meeting;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.AdRepository;
import adeuxpas.back.repository.MeetingRepository;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Implementation class for the MeetingService interface.
 * <p>
 * This service class provides implementations for meeting-related operations.
 * </p>
 * <p>
 * It interacts with the MeetingRepository to perform database operations related to meetings.
 * </p>
 */
@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingMapper meetingMapper;
    private final AdRepository adRepository;
    private final StripePaymentService stripePaymentService;

    /**
     * Constructor for MeetingServiceImpl.
     *
     * @param meetingRepository The MeetingRepository for interacting with meeting-related database operations.
     * @param meetingMapper The MeetingMapper for converting entities to DTOs.
     */
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper, AdRepository adRepository, StripePaymentService stripePaymentService) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.adRepository = adRepository;
        this.stripePaymentService = stripePaymentService;
    }

    /**
     * Retrieves a list of meetings filtered by their status and sorted by date.
     *
     * @param status The status of the meetings to filter.
     * @return A list of meetings filtered by status and sorted by date.
     */
    @Override
    public List<MeetingDTO> getMeetingsByBuyerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndBuyerIdOrderByDateDesc(MeetingStatus.INITIALIZED ,id);
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    @Override
    public List<MeetingDTO> getMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrderByDateDesc(MeetingStatus.INITIALIZED, id);
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    @Override
    public List<MeetingDTO> getAcceptedMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrBuyerIdOrderByDateDesc(MeetingStatus.ACCEPTED, id, id);
        return meetings.stream()
                .map( meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    @Override
    public List<MeetingDTO> getDueMeetings(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        List<Meeting> meetings = meetingRepository.findMeetings(MeetingStatus.FINALIZED, id, now.toLocalDateTime());
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    @Override
    public Long initializeMeeting(ProposedMeetingRequestDTO meetingRequestDTO) {
        Meeting meeting = meetingMapper.proposedMeetingRequestDTOToMeeting(meetingRequestDTO);

        Optional<Ad> optionalAd = adRepository.findById(meetingRequestDTO.adId);
        if(optionalAd.isPresent()){
            Set<Ad> ads = new HashSet<>();
            ads.add(optionalAd.get());
            meeting.setAds(ads);
        }
        meeting.setStatus(MeetingStatus.INITIALIZED);

        Meeting savedMeeting = meetingRepository.save(meeting);
        return savedMeeting.getIdMeeting();
    }

    // Stub method chain: to be completed with business logic and called when a meeting is finalized
    // All it does for now is capture the Stripe Card Payment for demonstration and testing purposes
    @Override
    public void finalizeMeeting(Long meetingId) {
        Optional<Meeting> meetingToBeFinalized = this.meetingRepository.findById(meetingId);
        if(meetingToBeFinalized.isPresent()){
            String stripePaymentIntentId = meetingToBeFinalized.get().getStripePaymentIntentId();
            if(stripePaymentIntentId != null){
                try{
                    this.stripePaymentService.capturePayment(stripePaymentIntentId);
                } catch(StripeException stripeException){
                    System.err.println("Error capturing payment : " + stripeException.getMessage());
                    // handle exception
                }
            }
        }
    }
}
