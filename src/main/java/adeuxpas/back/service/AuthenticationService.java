package adeuxpas.back.service;

import adeuxpas.back.dto.LoginRequestDTO;
import adeuxpas.back.dto.SignupRequestDTO;

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
     * @param signupRequestDTO The signup request containing user information.
     * @return {@code true} if the user is successfully signed up, {@code false} otherwise.
     */
    boolean canDoSignup(SignupRequestDTO signupRequestDTO);

    /**
     * Abstract method that attempts to authenticate a user based on the provided login request.
     * @param loginRequestDTO The login request containing user credentials.
     * @return An optional containing the JWT string if authentication is successful, or an empty optional otherwise.
     */
    Optional<String> login(LoginRequestDTO loginRequestDTO);
}
