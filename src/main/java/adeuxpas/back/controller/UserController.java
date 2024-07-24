package adeuxpas.back.controller;

import adeuxpas.back.dto.UserAliasAndLocationResponseDTO;
import adeuxpas.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.persistence.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;

import java.util.Set;

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
@RequestMapping("/api/users")
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

    /**
     * Retrieves a set of unique cities and postal codes.
     *
     * <p>
     * This endpoint fetches unique combinations of cities and postal codes from the
     * UserService.
     * If successful, it returns a ResponseEntity containing the set of
     * CityAndPostalCodeResponseDTO objects.
     * If an exception occurs during the operation, it returns a 500 Internal Server
     * Error response
     * with an error message in the response body.
     * </p>
     *
     * @return a ResponseEntity containing a set of unique
     *         CityAndPostalCodeResponseDTO objects
     *         if successful, or a 500 Internal Server Error response if an
     *         exception occurs
     *
     * @see UserAliasAndLocationResponseDTO
     */
    @Operation(summary = "Retrieves a set of unique cities and postal codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of unique cities and postal codes"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/cities-and-postal-codes")
    public ResponseEntity<Object> getUniqueCitiesAndPostalCodes() {
        try {
            Set<UserAliasAndLocationResponseDTO> cityAndPostalCodeResponseDTOs = this.userService
                    .getUniqueCitiesAndPostalCodes();
            return ResponseEntity.ok(cityAndPostalCodeResponseDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access the user's alias and location.
     *
     * @param id the user ID
     * @return a ResponseEntity with the user's alias, city and postal code.
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "Retrieves a user's alias and location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of sellers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("{id}/alias-and-location")
    public ResponseEntity<Object> getUserAliasAndLocation(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.userService.getUserAliasAndLocation(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access a user's profile information.
     *
     * @param userAlias The user Alias.
     * @return a ResponseEntity with the user profile information if successful,
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "User's profile information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of user profile information"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{userAlias}/presentation")
    public ResponseEntity<Object> getUserInformation(@PathVariable String userAlias) {
        try {
            return ResponseEntity.ok(userService.findUserProfileInfoByAlias(userAlias));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * Endpoint to access the sellers nearby.
     *
     * @param id the user ID
     * @return a ResponseEntity with the sellers which have the same postal code
     *         as the user
     *         or a 500 Internal Server Error response if an exception occurs.
     */
    @Operation(summary = "Retrieves sellers nearby from a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of sellers"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("{id}/nearby-sellers")
    public ResponseEntity<Object> getSellersNearby(@PathVariable long id) {
        try {
            return ResponseEntity.ok(this.userService.getSellersNearby(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
