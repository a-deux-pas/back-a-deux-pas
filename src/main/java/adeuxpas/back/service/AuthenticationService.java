package adeuxpas.back.service;

import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;

import java.util.Optional;

/**
 * Interface defining authentication-related operations for the application.
 * <p>
 * This service interface declares contracts for user authentication operations, such as signup and login, that must
 * be respected by all implementing classes
 * </p>
 *
 * @author Mircea Bardan
 */
public interface AuthenticationService {

    /**
     * Abstract method that validates the signup request and creates a new user account if the user does not already exist.
     * @param signupRequest The signup request containing user information.
     * @return {@code true} if the user is successfully signed up, {@code false} otherwise.
     */
    boolean canDoSignup(SignupRequest signupRequest);

    /**
     * Abstract method that attempts to authenticate a user based on the provided login request.
     * @param loginRequest The login request containing user credentials.
     * @return An optional containing the JWT string if authentication is successful, or an empty optional otherwise.
     */
    Optional<String> login(LoginRequest loginRequest);
}
