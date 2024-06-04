package adeuxpas.back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import adeuxpas.back.service.UserService;

import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Controller class for handling user-related endpoints.
 * <p>
 * This controller provides endpoints for user data.
 * </p>
 * <p>
 * It interacts with the UserService to fetch and post user data.
 * </p>
 *
 * @author Léa Hadida
 */
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController.
     *
     * @param userService for handling operations concerning users.
     */
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    // RAF : méthodes à changer une fois le login réalisé
    /**
     * Endpoint to access a user's profile information.
     *
     * @return a ResponseEntity with the user profile information if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's profile information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user profile information"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/account/profile/presentation")
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
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's preferred schedules")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user preferred schedules"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/account/profile/schedules")
    public ResponseEntity<Object> getPreferredShedules() {
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
     * @return a ResponseEntity with the user preferred meeting places if
     *         successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's preferred meeting places")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user preferred meeting places"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/account/profile/meeting-places")
    public ResponseEntity<Object> getPreferredMeetingPlaces() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredMeetingPlacesByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access user's details.
     *
     * @return a ResponseEntity with the user's details if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user's details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/account/profile/{userEmail}")
    public ResponseEntity<Object> getUserDetails(@PathVariable String userEmail) {
        try {
            return ResponseEntity.ok(userService.findUserByEmail(userEmail));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
