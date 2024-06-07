package adeuxpas.back.service;

import adeuxpas.back.auth.JWTService;
import adeuxpas.back.dto.CredentialsRequestDTO;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Implementation class for the AuthenticationService interface.
 * <p>
 * This service class provides implementations for user authentication
 * operations, such as signup and login.
 * </p>
 * <p>
 * It interacts with the UserService, BCryptPasswordEncoder, and JWTService to
 * perform authentication tasks.
 * </p>
 * This class is responsible for validating signup requests, creating new user
 * accounts,
 * and generating authentication tokens for login requests.
 *
 * @author Mircea Bardan
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final JWTService jwtService;

    /**
     * Constructor for AuthenticationServiceImpl.
     *
     * @param userService The UserService for interacting with user-related
     *                    operations.
     * @param encoder     The BCryptPasswordEncoder for encoding passwords.
     * @param jwtService  The JWTService for generating authentication tokens.
     */
    public AuthenticationServiceImpl(@Autowired UserService userService,
            @Autowired BCryptPasswordEncoder encoder,
            @Autowired JWTService jwtService) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    /**
     * Method to generate and returns a JWT token representing the user's session.
     *
     * @param user optional user.
     * @return a JWT token if user exists, or an empty optional otherwise.
     * 
     */
    @Override
    public Optional<String> createToken(Optional<User> user) {
        if (user.isPresent()) {
            String token = this.jwtService.generateToken(user.get().getEmail(), user.get().getRole());
            return Optional.of(token);
        }
        return Optional.empty();
    }

    /**
     * Validates the signup request and creates a new user account if the user does
     * not already exist.
     * If registration is successful, it generates and returns a JWT token.
     * If authentication fails, it returns an empty optional.
     *
     * @param credentialsRequestDTO user credentials.
     * @return An optional containing the JWT token if authentication is successful,
     *         or an empty optional otherwise.
     */
    @Override
    public Optional<String> signup(CredentialsRequestDTO credentialsRequestDTO) {
        Optional<User> userFromDB = this.userService.findUserByEmail(credentialsRequestDTO.getEmail());
        if (userFromDB.isEmpty()) {
            User userToSave = new User();
            userToSave.setEmail(credentialsRequestDTO.getEmail());
            userToSave.setPassword(encoder.encode(credentialsRequestDTO.getPassword()));
            userToSave.setInscriptionDate(LocalDateTime.now());
            userToSave.setRole(UserRole.USER);
            userToSave.setAccountStatus(AccountStatus.ACTIVE);
            this.userService.save(userToSave);
        }
        Optional<User> savedUser = this.userService.findUserByEmail(credentialsRequestDTO.getEmail());
        return createToken(savedUser);
    }

    /**
     * Validates the login request and attempts to authenticate the user.
     * If authentication is successful, it generates and returns a JWT token.
     * If authentication fails, it returns an empty optional.
     *
     * @param credentialsRequestDTO user credentials.
     * @return An optional containing the JWT token if authentication is successful,
     *         or an empty optional otherwise.
     */
    @Override
    public Optional<String> login(CredentialsRequestDTO credentialsRequestDTO) {
        Optional<User> userFromDB = this.userService.findUserByEmail(credentialsRequestDTO.getEmail());
        if (userFromDB.isPresent()
                && encoder.matches(credentialsRequestDTO.getPassword(), userFromDB.get().getPassword())) {
            return createToken(userFromDB);
        }
        return Optional.empty();
    }
}
