package adeuxpas.back.controller;

import adeuxpas.back.dto.UserProfileRequestDTO;
import adeuxpas.back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling account-related endpoints.
 * <p>
 * This controller provides endpoints for account-related data.
 * </p>
 * <p>
 * It interacts with the UserService.
 * </p>
 *
 * @author Léa Hadida
 */
@RequestMapping("/api/account")
@RestController
public class AccountController {

    private final UserService userService;

    /**
     * Constructor for AccountController.
     *
     * @param userService for handling operations concerning users' accounts.
     */
    public AccountController(@Autowired UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to create a user's profile
     *
     * @return a ResponseEntity with a 200 code if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's profile creation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile saved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping("/create")
    public ResponseEntity<Object> createProfile(@RequestBody @Valid UserProfileRequestDTO profileDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        try {
            userService.createProfile(profileDto);
            return ResponseEntity.ok("Profile saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
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
    @GetMapping("/profile/presentation")
    public ResponseEntity<Object> getUserInformation() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findUserProfileInfoById(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
    @GetMapping("/profile/schedules")
    public ResponseEntity<Object> getPreferredSchedules() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredSchedulesByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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
    @GetMapping("/profile/meeting-places")
    public ResponseEntity<Object> getPreferredMeetingPlaces() {
        Long userId = 3L;
        try {
            return ResponseEntity.ok(userService.findPreferredMeetingPlacesByUserId(userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
