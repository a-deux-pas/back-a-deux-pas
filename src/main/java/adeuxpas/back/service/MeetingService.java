package adeuxpas.back.service;

import adeuxpas.back.dto.MeetingDisplayDTO;
import adeuxpas.back.enums.MeetingStatus;

import java.util.List;

/**
 * Interface defining meeting-related operations for the application.
 * <p>
 * This service interface declares contracts for meeting-related operations that must be respected by all implementing classes.
 * </p>
 */
public interface MeetingService {

    /**
     * Retrieves a list of meetings filtered by their status and sorted by date.
     *
     * @param status The status of the meetings to filter.
     * @return A list of meetings filtered by status and sorted by date.
     */
    List<MeetingDisplayDTO> getMeetingsByBuyerId(Long id);

    List<MeetingDisplayDTO> getMeetingsBySellerId(Long id);

    List<MeetingDisplayDTO> getAcceptedMeetingsBySellerId(Long id);

    List<MeetingDisplayDTO> getDueMeetings(Long id);
}
