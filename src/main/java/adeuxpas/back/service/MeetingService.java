package adeuxpas.back.service;

import adeuxpas.back.dto.meeting.MeetingResponseDTO;
import adeuxpas.back.dto.meeting.MeetingRequestDTO;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Interface defining meeting-related operations for the application.
 * <p>
 * This service interface declares contracts for meeting-related operations that
 * must be respected by all implementing classes.
 * </p>
 */
public interface MeetingService {

    List<MeetingResponseDTO> getMeetingsByBuyerId(Long id);

    List<MeetingResponseDTO> getMeetingsBySellerId(Long id);

    List<MeetingResponseDTO> getAcceptedMeetingsBySellerId(Long id);

    List<MeetingResponseDTO> getDueMeetings(Long id);

    Optional<MeetingResponseDTO> acceptMeeting(Long meetingId);

    /**
     * Retrieves the alias of the buyer associated with the specified ad ID.
     *
     * @param adId The ID of the ad whose buyer's alias is to be retrieved.
     * @return The alias of the buyer for the specified ad.
     */
    String getBuyer(Long adId);

    /**
     * Retrieves the date and time of the meeting associated with the specified ad
     * ID.
     *
     * @param adId The ID of the ad whose meeting date is to be retrieved.
     * @return The date and time of the meeting for the specified ad.
     */
    LocalDateTime getMeetingDate(Long adId);

    /**
     * Contract to initialize a meeting
     *
     * @param meetingRequestDTO The DTo containing the request data needed for the
     *                          operation.
     */
    Long initializeMeeting(MeetingRequestDTO meetingRequestDTO);

    /**
     * Contract to finalize a meeting
     *
     * @param meetingId The id of the meeting to be finalized.
     */
    MeetingResponseDTO finalizeMeeting(Long meetingId, Long userId);
}
