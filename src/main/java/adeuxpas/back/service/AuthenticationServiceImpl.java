package adeuxpas.back.service;

import adeuxpas.back.auth.JWTService;
import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final BCryptPasswordEncoder encoder;
    private final JWTService jwtService;

    public AuthenticationServiceImpl(@Autowired UserService userService,
                                     @Autowired BCryptPasswordEncoder encoder,
                                     @Autowired JWTService jwtService){
        this.userService = userService;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @Override
    public boolean canDoSignup(SignupRequest signupRequest){
        Optional<User> userFromDB = this.userService.findUserByEmail(signupRequest.getEmail());
        if (userFromDB.isEmpty()){
            User userToSave = new User();
            userToSave.setEmail(signupRequest.getEmail());
            userToSave.setPassword(encoder.encode(signupRequest.getPassword()));
            userToSave.setAlias(signupRequest.getAlias());
            userToSave.setBio(signupRequest.getBio());
            userToSave.setCity(signupRequest.getCity());
            userToSave.setCountry(signupRequest.getCountry());
            userToSave.setStreet(signupRequest.getStreet());
            userToSave.setPostalCode(signupRequest.getPostalCode());
            userToSave.setProfilePicture(signupRequest.getProfilePicture());
            userToSave.setInscriptionDate(LocalDateTime.now());
            userToSave.setRole(UserRole.USER);
            userToSave.setAccountStatus(AccountStatus.ACTIVE);

            this.userService.save(userToSave);

            return true;
        }
        return false;
    }

    @Override
    public Optional<String> login(LoginRequest loginRequest) {
        Optional<User> userFromDB = this.userService.findUserByEmail(loginRequest.getEmail());
        if (userFromDB.isPresent() && encoder.matches(loginRequest.getPassword(), userFromDB.get().getPassword())){
            String token = this.jwtService.generateToken(userFromDB.get().getEmail(), userFromDB.get().getRole());
            return Optional.of(token);
        }

        return Optional.empty();
    }
}
