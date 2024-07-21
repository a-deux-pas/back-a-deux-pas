package adeuxpas.back.controller;

import adeuxpas.back.dto.*;
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
    public List<MeetingDTO> getMeetingsByBuyerId(@PathVariable Long id) {
        return meetingService.getMeetingsByBuyerId(id);
    }

    @GetMapping("/toBeConfirmed/{id}")
    public List<MeetingDTO> getMeetingsBySellerId(@PathVariable Long id) {
        return meetingService.getMeetingsBySellerId(id);
    }
    @GetMapping("/planned/{id}")
    public List<MeetingDTO> getAcceptedMeetingsBySellerId(@PathVariable Long id) {
        return meetingService.getAcceptedMeetingsBySellerId(id);
    }

    @GetMapping("/toBeFinalized/{id}")
    public List<MeetingDTO> getDueMeetings(@PathVariable Long id) {
        return meetingService.getDueMeetings(id);
    }
}
