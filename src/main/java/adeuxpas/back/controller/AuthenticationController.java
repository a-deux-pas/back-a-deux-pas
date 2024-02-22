package adeuxpas.back.controller;

import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;
import adeuxpas.back.service.AuthenticationService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class for handling authentication-related endpoints.
 * <p>
 * This controller provides endpoints for user authentication, including sign up and log in.
 * </p>
 * <p>
 * It interacts with the AuthenticationService to perform authentication operations.
 * </p>
 *
 * @author Mircea Bardan
 */

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    /**
     * Constructor for AuthenticationController.
     *
     * @param authenticationService The AuthenticationService for handling authentication operations.
     */
    public AuthenticationController(@Autowired AuthenticationService authenticationService){
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint to access the sign-up page.
     *
     * @return ResponseEntity with a 200(OK) status and a body containing a welcome message for accessing the sign-up page.
     */
    @GetMapping("/signup")
    public ResponseEntity<String> accessSignup(){
        return ResponseEntity.ok("Welcome, EVERYBODY. Please SIGN UP using the POST http method on this endpoint");
    }

    /**
     * Endpoint to access the login page.
     *
     * @return ResponseEntity with a 200(OK) status and a body containing a welcome message for accessing the login page.
     */
    @GetMapping("/login")
    public ResponseEntity<String> accessLogin(){
        return ResponseEntity.ok("Welcome, EVERYBODY. Please LOG IN using the POST http method on this endpoint");
    }

    // Test endpoint to imitate access to a secured page; to be REMOVED / REPLACED
    @GetMapping("/content")
    public ResponseEntity<String> accessContent(){
        return ResponseEntity.ok("Welcome, USER/ADMIN");
    }

    // Test endpoint to imitate access to a restricted page; to be REMOVED / REPLACED
    @GetMapping("/admin-page")
    public ResponseEntity<String> accessAdminPage(){
        return ResponseEntity.ok("Welcome, ADMIN");
    }

    /**
     * Endpoint to handle user sign-up requests.
     *
     * @param signupRequest The sign-up request containing user details.
     * @return ResponseEntity indicating the status of the sign-up operation.
     */
    @PostMapping("/signup")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User signed up successfully"),
            @ApiResponse(responseCode = "409", description = "User already exists")
    })
    public ResponseEntity<String> signUp(@RequestBody SignupRequest signupRequest) {
        if (this.authenticationService.canDoSignup(signupRequest))
            return ResponseEntity.ok().body("User signed up successfully with email: " + signupRequest.getEmail());
        else
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("A user with email '" + signupRequest.getEmail() + "' already exists");
    }

    /**
     * Endpoint to handle user login requests.
     *
     * @param loginRequest The login request containing user credentials.
     * @return ResponseEntity containing the authentication token if login is successful, or an error message otherwise.
     */
    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid email and/or password")
    })
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        String token = this.authenticationService.login(loginRequest)
                .orElse(null);

        if (token != null)
            return ResponseEntity.ok(token);
        else
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email and/or password");
    }
}
