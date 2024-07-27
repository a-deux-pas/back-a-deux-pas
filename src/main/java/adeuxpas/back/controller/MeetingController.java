package adeuxpas.back.controller;

import adeuxpas.back.dto.*;
import adeuxpas.back.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    public MeetingController(@Autowired MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * GET /proposed/:id : Get all proposed meetings for a buyer.
     *
     * @param id The ID of the buyer.
     * @return A ResponseEntity containing a list of meetings filtered by buyer ID.
     */
    @GetMapping("/proposed/{id}")
    public ResponseEntity<List<MeetingDTO>> getMeetingsByBuyerId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(meetingService.getMeetingsByBuyerId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /toBeConfirmed/:id : Get all meetings to be confirmed for a seller.
     *
     * @param id The ID of the seller.
     * @return A ResponseEntity containing a list of meetings filtered by seller ID.
     */
    @GetMapping("/toBeConfirmed/{id}")
    public ResponseEntity<List<MeetingDTO>> getMeetingsBySellerId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(meetingService.getMeetingsBySellerId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /planned/:id : Get all accepted meetings for a seller.
     *
     * @param id The ID of the seller.
     * @return A ResponseEntity containing a list of accepted meetings filtered by
     *         seller ID.
     */
    @GetMapping("/planned/{id}")
    public ResponseEntity<List<MeetingDTO>> getAcceptedMeetingsBySellerId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(meetingService.getAcceptedMeetingsBySellerId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * GET /toBeFinalized/:id : Get all due meetings for a user.
     *
     * @param id The ID of the user.
     * @return A ResponseEntity containing a list of due meetings filtered by user
     *         ID.
     */
    @GetMapping("/toBeFinalized/{id}")
    public ResponseEntity<List<MeetingDTO>> getDueMeetings(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(meetingService.getDueMeetings(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * PUT /:id/accept : Accept a meeting.
     *
     * @param id The ID of the meeting to accept.
     * @return A ResponseEntity containing the updated MeetingDTO if found, or a not
     *         found status.
     */
    @PutMapping("/{id}/accept")
    public ResponseEntity<MeetingDTO> acceptMeeting(@PathVariable Long id) {
        try {
            return meetingService.acceptMeeting(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Retrieves the alias of the buyer associated with the specified ad
     * ID.
     *
     * @param adId The ID of the ad for which the buyer's alias is to be
     *             retrieved.
     * @return A ResponseEntity containing the buyer's alias if successful, or an
     *         error message if an exception occurs.
     * @throws Exception if there is an error while retrieving the buyer's alias.
     */
    @GetMapping("/{adId}/buyer")
    public ResponseEntity<Object> getBuyer(@PathVariable Long adId) {
        try {
            return ResponseEntity.ok(meetingService.getBuyer(adId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Retrieves the date and time of the meeting associated with the specified
     * ad ID.
     *
     * @param adId The ID of the ad for which the meeting date and time
     *             is to be retrieved.
     * @return A ResponseEntity containing the meeting date and time if successful,
     *         or an error message if an exception occurs.
     * @throws Exception if there is an error while retrieving the meeting date and
     *                   time.
     */
    @GetMapping("/{adId}/date")
    public ResponseEntity<Object> getMeetingDate(@PathVariable Long adId) {
        try {
            return ResponseEntity.ok(meetingService.getMeetingDate(adId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
