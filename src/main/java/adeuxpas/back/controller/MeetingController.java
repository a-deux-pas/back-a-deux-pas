package adeuxpas.back.controller;

import adeuxpas.back.dto.MeetingDisplayDTO;
import adeuxpas.back.enums.MeetingStatus;
import adeuxpas.back.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing meetings.
 */
@RestController
@RequestMapping("/api/meetings")
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
    public List<MeetingDisplayDTO> getMeetingsByBuyerId(@PathVariable Long id) {
        return meetingService.getMeetingsByBuyerId(id);
    }

    @GetMapping("/toBeConfirmed/{id}")
    public List<MeetingDisplayDTO> getMeetingsBySellerId(@PathVariable Long id) {
        return meetingService.getMeetingsBySellerId(id);
    }
    @GetMapping("/planned/{id}")
    public List<MeetingDisplayDTO> getAcceptedMeetingsBySellerId(@PathVariable Long id) {
        return meetingService.getAcceptedMeetingsBySellerId(id);
    }

    @GetMapping("/toBeFinalized/{id}")
    public List<MeetingDisplayDTO> getDueMeetings(@PathVariable Long id) {
        return meetingService.getDueMeetings(id);
    }
}
