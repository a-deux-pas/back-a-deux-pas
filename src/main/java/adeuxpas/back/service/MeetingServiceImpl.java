package adeuxpas.back.service;

import adeuxpas.back.dto.*;
import adeuxpas.back.dto.mapper.*;
import adeuxpas.back.entity.Meeting;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

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

    /**
     * Constructor for MeetingServiceImpl.
     *
     * @param meetingRepository The MeetingRepository for interacting with meeting-related database operations.
     * @param meetingMapper The MeetingMapper for converting entities to DTOs.
     */

    public MeetingServiceImpl(@Autowired MeetingRepository meetingRepository, @Autowired MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
    }

    /**
     * Retrieves a list of meetings for a specific buyer.
     *
     * @param id The ID of the buyer.
     * @return A list of MeetingDTO objects representing meetings for the buyer,
     *         filtered by INITIALIZED status and future dates.
     */
    @Override
    public List<MeetingDTO> getMeetingsByBuyerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndBuyerIdOrderByDateDesc(MeetingStatus.INITIALIZED, id);
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
    public List<MeetingDTO> getMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrderByDateDesc(MeetingStatus.INITIALIZED, id);
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
     * @return A list of MeetingDTO objects representing accepted meetings for the user,
     *         with status updated to PLANNED for past meetings.
     */
    @Override
    public List<MeetingDTO> getAcceptedMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrBuyerIdOrderByDateDesc(MeetingStatus.ACCEPTED, id, id);
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
    public List<MeetingDTO> getDueMeetings(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        List<Meeting> meetings = meetingRepository.findPastMeetings(id, now.toLocalDateTime());
        return meetings.stream()
                .map(meeting -> {
                    if (meeting.getStatus() == MeetingStatus.ACCEPTED && meeting.getDate().isBefore(now.toLocalDateTime())) {
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
     * @return An Optional containing the updated MeetingDTO if the meeting was found and accepted,
     *         or an empty Optional if the meeting was not found or couldn't be accepted.
     */
    @Override
    public Optional<MeetingDTO> acceptMeeting(Long meetingId) {
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
}
