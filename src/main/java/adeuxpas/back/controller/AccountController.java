package adeuxpas.back.controller;

import adeuxpas.back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller class for handling account-related endpoints.
 * <p>
 * This controller provides endpoints for user account data.
 * </p>
 * <p>
 * It interacts with the UserService to fetch and post user data.
 * </p>
 *
 * @author Léa Hadida
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final UserService userService;

    /**
     * Constructor for AccountController.
     *
     * @param userService for handling operations concerning users.
     */
    public AccountController(@Autowired UserService userService) {
        this.userService = userService;
    }

    // RAF : méthodes à changer une fois le login réalisé

    /**
     * Endpoint to access a user's profile information.
     *
     * @return a ResponseEntity with the user profile information if successful,
     * or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's profile information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user profile information"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/profile/presentation")
    public ResponseEntity<Object> getUserInformation() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findUserProfileInfoById(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access a user's preferred schedules.
     *
     * @return a ResponseEntity with the user preferred schedules if successful,
     * or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's preferred schedules")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user preferred schedules"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/profile/schedules")
    public ResponseEntity<Object> getPreferredSchedules() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredSchedulesByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access user's preferred meeting places.
     *
     * @return a ResponseEntity with the user preferred meeting places if successful,
     * or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's preferred meeting places")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user preferred meeting places"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/profile/meeting-places")
    public ResponseEntity<Object> getPreferredMeetingPlaces() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredMeetingPlacesByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}