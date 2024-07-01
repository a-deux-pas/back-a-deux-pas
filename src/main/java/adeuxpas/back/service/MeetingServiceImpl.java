package adeuxpas.back.service;

import adeuxpas.back.dto.MeetingDisplayDTO;
import adeuxpas.back.dto.mapper.MeetingMapper;
import adeuxpas.back.entity.Meeting;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<MeetingDisplayDTO> getMeetingsByBuyerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByBuyerIdOrderByDateDesc(id);
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDisplayDTO)
                .collect(Collectors.toList());
    }

    public List<MeetingDisplayDTO> getMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findBySellerIdOrderByDateDesc(id);
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDisplayDTO)
                .collect(Collectors.toList());
    }

    public List<MeetingDisplayDTO> getAcceptedMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrderByDateDesc(MeetingStatus.ACCEPTED, id);
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDisplayDTO)
                .collect(Collectors.toList());
    }

    public List<MeetingDisplayDTO> getDueMeetings(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        List<Meeting> meetings = meetingRepository.findMeetings(id, id, now.toLocalDateTime());
        return meetings.stream()
                .map(meetingMapper::meetingToMeetingDisplayDTO)
                .collect(Collectors.toList());
    }
}
