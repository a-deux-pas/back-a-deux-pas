package adeuxpas.back.controller;

import adeuxpas.back.dto.*;
import adeuxpas.back.service.MeetingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/initialize")
    public ResponseEntity<Object> initializeMeeting(@RequestBody @Valid ProposedMeetingRequestDTO meetingRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        try {
            Long meetingId = meetingService.initializeMeeting(meetingRequestDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Meeting created successfully");
            response.put("meetingId", meetingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create meeting : error occurred in the meeting service");
        }
    }

    // Stub method chain: to be completed with business logic and called when a meeting is finalized
    // All it does for now is capture the Stripe Card Payment for demonstration and testing purposes
    @GetMapping("/finalize/{meetingId}")
    public ResponseEntity<Object> finalizeMeeting(@PathVariable Long meetingId) {
        try {
            this.meetingService.finalizeMeeting(meetingId);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Meeting finalized successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to finalize meeting : error occurred in the meeting service");
        }
    }
}
