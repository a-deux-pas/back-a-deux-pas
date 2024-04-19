package adeuxpas.back.service;

import adeuxpas.back.auth.JWTService;
import adeuxpas.back.dto.LoginRequestDTO;
import adeuxpas.back.dto.SignupRequestDTO;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
     * Validates the signup request and creates a new user account if the user does
     * not already exist.
     *
     * @param signupRequestDTO The signup request containing user information.
     * @return {@code true} if the user is successfully signed up, {@code false}
     *         otherwise.
     */
    @Override
    public boolean canDoSignup(SignupRequestDTO signupRequestDTO) {
        Optional<User> userFromDB = this.userService.findUserByEmail(signupRequestDTO.getEmail());
        if (userFromDB.isEmpty()) {
            User userToSave = new User();
            userToSave.setEmail(signupRequestDTO.getEmail());
            userToSave.setPassword(encoder.encode(signupRequestDTO.getPassword()));
            userToSave.setAlias(signupRequestDTO.getAlias());
            userToSave.setBio(signupRequestDTO.getBio());
            userToSave.setCity(signupRequestDTO.getCity());
            userToSave.setCountry(signupRequestDTO.getCountry());
            userToSave.setStreet(signupRequestDTO.getStreet());
            userToSave.setPostalCode(signupRequestDTO.getPostalCode());
            userToSave.setProfilePicture(signupRequestDTO.getProfilePicture());
            userToSave.setInscriptionDate(LocalDate.now());
            userToSave.setRole(UserRole.USER);
            userToSave.setAccountStatus(AccountStatus.ACTIVE);

            this.userService.save(userToSave);

            return true;
        }
        return false;
    }

    /**
     * Validates the login request and attempts to authenticate the user.
     * If authentication is successful, it generates and returns a JWT token
     * representing the user's session.
     * If authentication fails, it returns an empty optional.
     *
     * @param loginRequestDTO The login request containing user credentials.
     * @return An optional containing the JWT token if authentication is successful,
     *         or an empty optional otherwise.
     */
    @Override
    public Optional<String> login(LoginRequestDTO loginRequestDTO) {
        Optional<User> userFromDB = this.userService.findUserByEmail(loginRequestDTO.getEmail());
        if (userFromDB.isPresent() && encoder.matches(loginRequestDTO.getPassword(), userFromDB.get().getPassword())) {
            String token = this.jwtService.generateToken(userFromDB.get().getEmail(), userFromDB.get().getRole());
            return Optional.of(token);
        }

        return Optional.empty();
    }
}
