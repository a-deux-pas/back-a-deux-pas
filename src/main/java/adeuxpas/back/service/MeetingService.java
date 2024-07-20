package adeuxpas.back.service;

import adeuxpas.back.dto.MeetingFinalizedDTO;
import adeuxpas.back.dto.MeetingProposedDTO;
import adeuxpas.back.dto.MeetingToConfirmDTO;
import adeuxpas.back.dto.MeetingPlannedDTO;
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
    List<MeetingProposedDTO> getMeetingsByBuyerId(Long id);

    List<MeetingToConfirmDTO> getMeetingsBySellerId(Long id);

    List<MeetingPlannedDTO> getAcceptedMeetingsBySellerId(Long id);

    List<MeetingFinalizedDTO> getDueMeetings(Long id);
}
