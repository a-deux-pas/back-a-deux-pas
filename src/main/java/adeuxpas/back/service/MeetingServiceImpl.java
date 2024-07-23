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
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingMapper meetingMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
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
        LocalDateTime now = LocalDateTime.now();
        return meetings.stream()
                .filter(meeting -> meeting.getDate().isAfter(now))
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    @Override
    public List<MeetingDTO> getMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrderByDateDesc(MeetingStatus.INITIALIZED, id);
        LocalDateTime now = LocalDateTime.now();
        return meetings.stream()
                .filter(meeting -> meeting.getDate().isAfter(now))
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }

    @Override
    public List<MeetingDTO> getAcceptedMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrBuyerIdOrderByDateDesc(MeetingStatus.ACCEPTED, id, id);
        LocalDateTime now = LocalDateTime.now();
        return meetings.stream()
                .map(meeting -> {
                    if (meeting.getDate().isBefore(now)) {
                        meeting.setStatus(MeetingStatus.PLANNED);
                        meetingRepository.save(meeting);
                    }
                    return meetingMapper.meetingToMeetingDTO(meeting);
                })
                .toList();
    }

    @Override
    public List<MeetingDTO> getDueMeetings(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        List<Meeting> meetings = meetingRepository.findPastMeetings(id, now.toLocalDateTime());
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDTO)
                .toList();
    }


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
