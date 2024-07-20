package adeuxpas.back.controller;

import adeuxpas.back.dto.MeetingFinalizedDTO;
import adeuxpas.back.dto.MeetingPlannedDTO;
import adeuxpas.back.dto.MeetingProposedDTO;
import adeuxpas.back.dto.MeetingToConfirmDTO;
import adeuxpas.back.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing meetings.
 */
@RestController
@RequestMapping("/api/meetings")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class MeetingController {

    private final MeetingService meetingService;

    /**
     * Constructor for MeetingController.
     *
     * @param meetingService The MeetingService for meeting-related operations.
     */
    @Autowired
    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * GET /:status : Get all meetings by status.
     *
     * @param status The status of the meetings to retrieve.
     * @return A list of meetings filtered by status and sorted by date.
     */
    @GetMapping("/proposed/{id}")
    public List<MeetingProposedDTO> getMeetingsByBuyerId(@PathVariable Long id) {
        return meetingService.getMeetingsByBuyerId(id);
    }

    @GetMapping("/toBeConfirmed/{id}")
    public List<MeetingToConfirmDTO> getMeetingsBySellerId(@PathVariable Long id) {
        return meetingService.getMeetingsBySellerId(id);
    }
    @GetMapping("/planned/{id}")
    public List<MeetingPlannedDTO> getAcceptedMeetingsBySellerId(@PathVariable Long id) {
        return meetingService.getAcceptedMeetingsBySellerId(id);
    }

    @GetMapping("/toBeFinalized/{id}")
    public List<MeetingFinalizedDTO> getDueMeetings(@PathVariable Long id) {
        return meetingService.getDueMeetings(id);
    }
}
