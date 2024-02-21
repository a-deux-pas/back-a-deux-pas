package adeuxpas.back.controller;

import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;
import adeuxpas.back.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
@RequestMapping(("/api"))
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
     * @return A welcome message for accessing the sign-up page.
     */
    @GetMapping("/signup")
    public String accessSignup(){
        return "Welcome, EVERYBODY. Please SIGN UP using the POST http method on this endpoint";
    }

    /**
     * Endpoint to access the login page.
     *
     * @return A welcome message for accessing the login page.
     */
    @GetMapping("/login")
    public String accessLogin(){
        return "Welcome, EVERYBODY. Please LOG IN using the POST http method on this endpoint";
    }

    // Mock endpoint to imitate access to a secured page; to be REMOVED / REPLACED
    @GetMapping("/content")
    public String accessContent(){
        return "Welcome, USER/ADMIN";
    }

    // Mock endpoint to imitate access to a restricted page; to be REMOVED / REPLACED
    @GetMapping("/admin-page")
    public String accessAdminPage(){
        return "Welcome, ADMIN";
    }

    /**
     * Endpoint to handle user sign-up requests.
     *
     * @param signupRequest The sign-up request containing user details.
     * @return ResponseEntity indicating the status of the sign-up operation.
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest signupRequest){
        if (this.authenticationService.canDoSignup(signupRequest))
            return ResponseEntity.ok().body("User signed up successfully with email: " + signupRequest.getEmail());

        return ResponseEntity.status(403).body("A user with email '" + signupRequest.getEmail() + "' already exists");
    }

    /**
     * Endpoint to handle user login requests.
     *
     * @param loginRequest The login request containing user credentials.
     * @return ResponseEntity containing the authentication token if login is successful, or an error message otherwise.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        Optional<String> token = this.authenticationService.login(loginRequest);
        if (token.isPresent())
            return ResponseEntity.ok(token);

        return ResponseEntity.status(401).body("Invalid email and/or password");
    }
}
