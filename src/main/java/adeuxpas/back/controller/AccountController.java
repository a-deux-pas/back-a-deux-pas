package adeuxpas.back.controller;

import adeuxpas.back.dto.user.UserProfileRequestDTO;
import adeuxpas.back.service.UserService;
import adeuxpas.back.util.ValidationHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.validation.BindingResult;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
     * Endpoint to create a user's profile.
     *
     * @return a ResponseEntity with a 200 code if successful,
     *         a 400 Bad Request if errors,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "Creates user's profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile saved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PatchMapping(value = "/create")
    public ResponseEntity<Object> createProfile(
            @RequestPart("profileInfo") @Valid UserProfileRequestDTO profileDto,
            @RequestPart("profilePicture") MultipartFile profilePicture,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationHelper.getErrors(bindingResult));
        }
        try {
            userService.createProfile(profileDto, profilePicture);
            return ResponseEntity.ok("Profile saved successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access a user's preferred schedules.
     *
     * @param userId The user ID.
     * @return a ResponseEntity with the user preferred schedules if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "Retrieves a user's preferred schedules")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's preferred schedules retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{userId}/schedules")
    public ResponseEntity<Object> getPreferredSchedules(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(userService.findPreferredSchedulesByUserId(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access user's preferred meeting places.
     *
     * @param userId The user ID.
     * @return a ResponseEntity with the user preferred meeting places if
     *         successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "Retrieves a user's preferred meeting places")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User's preferred meeting places retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{userId}/meeting-places")
    public ResponseEntity<Object> getPreferredMeetingPlaces(@PathVariable long userId) {
        try {
            return ResponseEntity.ok(userService.findPreferredMeetingPlacesByUserId(userId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
