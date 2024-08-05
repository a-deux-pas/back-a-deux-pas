package adeuxpas.back.controller;

import adeuxpas.back.dto.meeting.MeetingResponseDTO;
import adeuxpas.back.dto.meeting.MeetingRequestDTO;
import adeuxpas.back.service.MeetingService;
import adeuxpas.back.util.ValidationHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    public MeetingController(@Autowired MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * GET /proposed/:id : Get all proposed meetings for a buyer.
     *
     * @param id The ID of the buyer.
     * @return A ResponseEntity containing a list of meetings filtered by buyer ID.
     */
    @Operation(summary = "Retrieves proposed meetings for a buyer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meetings retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/proposed/{id}")
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingsByBuyerId(@PathVariable Long id) {
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
    @Operation(summary = "Retrieves meetings to be confirmed for a seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meetings retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/toBeConfirmed/{id}")
    public ResponseEntity<List<MeetingResponseDTO>> getMeetingsBySellerId(@PathVariable Long id) {
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
    @Operation(summary = "Retrieves accepted meetings for a seller")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meetings retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/planned/{id}")
    public ResponseEntity<List<MeetingResponseDTO>> getAcceptedMeetingsBySellerId(@PathVariable Long id) {
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
    @Operation(summary = "Retrieves due meetings for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meetings retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/toBeFinalized/{id}")
    public ResponseEntity<List<MeetingResponseDTO>> getDueMeetings(@PathVariable Long id) {
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
    @Operation(summary = "Accepts a meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meeting accepted successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/{id}/accept")
    public ResponseEntity<MeetingResponseDTO> acceptMeeting(@PathVariable Long id) {
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
    @Operation(summary = "Retrieves the alias of the buyer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Buyer Alias retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Retrieves the date of the meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meeting date retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{adId}/date")
    public ResponseEntity<Object> getMeetingDate(@PathVariable Long adId) {
        try {
            return ResponseEntity.ok(meetingService.getMeetingDate(adId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Initializes a new meeting based on the provided {@link MeetingRequestDTO}.
     *
     * @param meetingRequestDTO the DTO containing the proposed meeting details
     * @param bindingResult     the result of the validation of the request body
     * @return a {@link ResponseEntity} containing either a success message and the
     *         meeting ID,
     *         or an error message if the validation or meeting creation fails
     */
    @Operation(summary = "Initializes a meeting")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meeting initialized successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/initialize")
    public ResponseEntity<Object> initializeMeeting(@RequestBody @Valid MeetingRequestDTO meetingRequestDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationHelper.getErrors(bindingResult));
        }
        try {
            Long meetingId = meetingService.initializeMeeting(meetingRequestDTO);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Meeting created successfully");
            // the meeting id is expected by the front end code, as it will
            // immediately call the create-payment-intent endpoint, and pass it this meeting
            // id
            response.put("meetingId", meetingId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create meeting : error occurred in the meeting service");
        }
    }

    // Stub method chain: to be completed with business logic and called when a
    // meeting is finalized
    // All it does for now is capture the Stripe Card Payment for demonstration and
    // testing purposes
    @GetMapping("/finalize/{meetingId}")
    public ResponseEntity<Object> finalizeMeeting(@PathVariable Long meetingId) {
        try {
            this.meetingService.finalizeMeeting(meetingId);
            return ResponseEntity.ok("Meeting finalized successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to finalize meeting : error occurred in the meeting service");
        }
    }
}
