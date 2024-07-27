package adeuxpas.back.service;

import adeuxpas.back.dto.*;

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

    /**
     * Retrieves a list of meetings filtered by their status and sorted by date.
     *
     * @param status The status of the meetings to filter.
     * @return A list of meetings filtered by status and sorted by date.
     */
    List<MeetingDTO> getMeetingsByBuyerId(Long id);

    List<MeetingDTO> getMeetingsBySellerId(Long id);

    List<MeetingDTO> getAcceptedMeetingsBySellerId(Long id);

    List<MeetingDTO> getDueMeetings(Long id);

    Optional<MeetingDTO> acceptMeeting(Long meetingId);

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
}
