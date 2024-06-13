package adeuxpas.back.controller;

import adeuxpas.back.dto.CredentialsRequestDTO;
import adeuxpas.back.service.AuthenticationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling authentication-related endpoints.
 * <p>
 * This controller provides endpoints for user authentication, including sign up
 * and log in.
 * </p>
 * <p>
 * It interacts with the AuthenticationService to perform authentication
 * operations.
 * </p>
 *
 * @author Mircea Bardan
 */

@RequestMapping("/api")
@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Constructor for AuthenticationController.
     *
     * @param authenticationService The AuthenticationService for handling
     *                              authentication operations.
     */
    public AuthenticationController(@Autowired AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // Test endpoint to imitate access to a restricted page; to be REMOVED /
    // REPLACED
    @GetMapping("/admin-page")
    public ResponseEntity<String> accessAdminPage() {
        return ResponseEntity.ok("Welcome, ADMIN");
    }

    /**
     * Endpoint to check if an email address already exists.
     *
     * @param email to check.
     * @return ResponseEntity indicating true or false.
     */
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(authenticationService.checkIfEmailAlreadyExist(email));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    /**
     * Endpoint to check if a password matches with a user address mail.
     *
     * @param email    user email.
     * @param password password to check.
     * @return ResponseEntity indicating true or false.
     */
    @GetMapping("/check-password")
    public ResponseEntity<Boolean> checkPassword(@RequestParam String email, String password) {
        try {
            return ResponseEntity.ok(authenticationService.checkIfPasswordMatchesWithEmail(email, password));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    /**
     * Endpoint to check if an Alias has already been taken by a user.
     *
     * @param alias alias to check.
     * @return ResponseEntity indicating true or false.
     */
    @GetMapping("/check-alias")
    public ResponseEntity<Boolean> checkAlias(@RequestParam String alias) {
        try {
            return ResponseEntity.ok(authenticationService.checkIfAliasAlreadyExist(alias));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    /**
     * Endpoint to handle a user sign-up request.
     *
     * @param credentialsRequestDTO The sign-up request containing user details.
     * @return ResponseEntity containing the authentication token if sign-up is
     *         successful, or an error message otherwise.
     */
    @PostMapping("/signup")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed up successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid email and/or password"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<Object> signUp(@RequestBody @Valid CredentialsRequestDTO credentialsRequestDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errorMap.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        try {
            String token = this.authenticationService.signup(credentialsRequestDTO).orElse(null);
            if (token != null)
                return ResponseEntity.status(HttpStatus.CREATED).body(token);
            else
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email and/or password");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    /**
     * Endpoint to handle user login requests.
     *
     * @param credentialsRequestDTO The login request containing user credentials.
     * @return ResponseEntity containing the authentication token if login is
     *         successful, or an error message otherwise.
     */
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid email and/or password")
    })
    public ResponseEntity<String> login(@RequestBody CredentialsRequestDTO credentialsRequestDTO) {
        String token = this.authenticationService.login(credentialsRequestDTO)
                .orElse(null);

        if (token != null)
            return ResponseEntity.ok(token);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email and/or password");
    }
}
