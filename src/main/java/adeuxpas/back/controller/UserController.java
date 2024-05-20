package adeuxpas.back.controller;

import adeuxpas.back.dto.CityAndPostalCodeResponseDTO;
import adeuxpas.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.responses.*;
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
    public UserController(@Autowired UserService userService){
        this.userService = userService;
    }

    /**
     * Retrieves a set of unique cities and postal codes.
     *
     * <p>This endpoint fetches unique combinations of cities and postal codes from the UserService.
     * If successful, it returns a ResponseEntity containing the set of CityAndPostalCodeResponseDTO objects.
     * If an exception occurs during the operation, it returns a 500 Internal Server Error response
     * with an error message in the response body.</p>
     *
     * @return a ResponseEntity containing a set of unique CityAndPostalCodeResponseDTO objects
     *         if successful, or a 500 Internal Server Error response if an exception occurs
     *
     * @see CityAndPostalCodeResponseDTO
     */
    @Operation(summary = "Retrieves a set of unique cities and postal codes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful retrieval of unique cities and postal codes"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/cities-and-postal-codes")
    public ResponseEntity<Object> getUniqueCitiesAndPostalCodes() {
        try {
            Set<CityAndPostalCodeResponseDTO> cityAndPostalCodeResponseDTOs = this.userService.getUniqueCitiesAndPostalCodes();
            return ResponseEntity.ok(cityAndPostalCodeResponseDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
