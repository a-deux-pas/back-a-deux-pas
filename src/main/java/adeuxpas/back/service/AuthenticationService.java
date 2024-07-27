package adeuxpas.back.service;

import adeuxpas.back.dto.CredentialsRequestDTO;

import java.util.Optional;

/**
 * Interface defining authentication-related operations for the application.
 * <p>
 * This service interface declares contracts for user authentication operations,
 * such as signup and login, that must
 * be respected by all implementing classes
 * </p>
 *
 * @author Mircea Bardan
 */
public interface AuthenticationService {

    /**
     * Abstract method that attempts to check if address mail exists and if password
     * matches with the address mail.
     * 
     * @param credentialsRequestDTO The user credentials.
     * @return true if the credentials are correct.
     */
    Boolean checkCredentials(CredentialsRequestDTO credentialsRequestDTO);

    /**
     * Abstract method that attempts to check if an email already exist.
     * 
     * @param email The email to check.
     * @return true if the email address already exists.
     */
    Boolean checkIfEmailAlreadyExist(String email);

    /**
     * Abstract method that attempts to check if an alias already exist.
     * 
     * @param alias The alias to check.
     * @return true if the alias already exists.
     */
    Boolean checkIfAliasAlreadyExist(String alias);

    /**
     * Abstract method that validates the signup request and creates a new user
     * account if the user does not already exist.
     * 
     * @param credentialsRequestDTO The user credentials.
     * @return An optional containing the JWT string if authentication is
     *         successful, or an empty optional otherwise.
     */
    Optional<String> signup(CredentialsRequestDTO credentialsRequestDTO);

    /**
     * Abstract method that attempts to authenticate a user based on the provided
     * login request.
     * 
     * @param credentialsRequestDTO The user credentials.
     * @return An optional containing the JWT string if authentication is
     *         successful, or an empty optional otherwise.
     */
    Optional<String> login(CredentialsRequestDTO credentialsRequestDTO);
}
