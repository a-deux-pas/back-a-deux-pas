package adeuxpas.back.service;

import adeuxpas.back.dto.MeetingFinalizedDTO;
import adeuxpas.back.dto.MeetingPlannedDTO;
import adeuxpas.back.dto.MeetingProposedDTO;
import adeuxpas.back.dto.MeetingToConfirmDTO;
import adeuxpas.back.dto.mapper.MeetingFinalizedMapper;
import adeuxpas.back.dto.mapper.MeetingPlannedMapper;
import adeuxpas.back.dto.mapper.MeetingProposedMapper;
import adeuxpas.back.dto.mapper.MeetingToConfirmMapper;
import adeuxpas.back.entity.Meeting;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private final MeetingProposedMapper meetingProposedMapper;
    private final MeetingToConfirmMapper meetingToConfirmMapper;
    private final MeetingPlannedMapper meetingPlannedMapper;
    private final MeetingFinalizedMapper meetingFinalizedMapper;

    /**
     * Constructor for MeetingServiceImpl.
     *
     * @param meetingRepository The MeetingRepository for interacting with meeting-related database operations.
     * @param meetingProposedMapper The MeetingMapper for converting entities to DTOs.
     * @param meetingToConfirmMapper The MeetingMapper for converting entities to DTOs.
     * @param meetingPlannedMapper The MeetingMapper for converting entities to DTOs.
     */
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository, MeetingProposedMapper meetingProposedMapper, MeetingToConfirmMapper meetingToConfirmMapper, MeetingPlannedMapper meetingPlannedMapper, MeetingFinalizedMapper meetingFinalizedMapper) {
        this.meetingRepository = meetingRepository;
        this.meetingProposedMapper = meetingProposedMapper;
        this.meetingToConfirmMapper = meetingToConfirmMapper;
        this.meetingPlannedMapper = meetingPlannedMapper;
        this.meetingFinalizedMapper = meetingFinalizedMapper;
    }

    /**
     * Retrieves a list of meetings filtered by their status and sorted by date.
     *
     * @param status The status of the meetings to filter.
     * @return A list of meetings filtered by status and sorted by date.
     */
    @Override
    public List<MeetingProposedDTO> getMeetingsByBuyerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndBuyerIdOrderByDateDesc(MeetingStatus.INITIALIZED ,id);
        return meetings.stream()
                .map(meetingProposedMapper::meetingToMeetingProposedDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingToConfirmDTO> getMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrderByDateDesc(MeetingStatus.INITIALIZED, id);
        return meetings.stream()
                .map(meetingToConfirmMapper::meetingToMeetingToConfirmDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingPlannedDTO> getAcceptedMeetingsBySellerId(Long id) {
        List<Meeting> meetings = meetingRepository.findByStatusAndSellerIdOrBuyerIdOrderByDateDesc(MeetingStatus.ACCEPTED, id, id);
        return meetings.stream()
                .map( meetingPlannedMapper::meetingToMeetingPlannedDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingFinalizedDTO> getDueMeetings(Long id) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        List<Meeting> meetings = meetingRepository.findMeetings(MeetingStatus.FINALIZED, id, now.toLocalDateTime());
        return meetings.stream()
                .map(meetingFinalizedMapper::meetingToMeetingFinalizedDTO)
                .collect(Collectors.toList());
    }
}
