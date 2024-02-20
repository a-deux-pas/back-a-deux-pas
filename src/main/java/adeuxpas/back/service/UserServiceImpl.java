package adeuxpas.back.service;

import adeuxpas.back.auth.JWTService;
import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.UserRole;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JWTService jwtService;

    public UserServiceImpl(@Autowired UserRepository userRepository,
                           @Autowired BCryptPasswordEncoder encoder,
                           @Autowired JWTService jwtService){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }
    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public boolean canDoSignup(SignupRequest signupRequest){
        Optional<User> userFromDB = this.findByEmail(signupRequest.getEmail());
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
            userToSave.setRole(UserRole.USER);

            this.userRepository.save(userToSave);

            return true;
        }
        return false;
    }

    @Override
    public Optional<String> login(LoginRequest loginRequest) {
        Optional<User> userFromDB = this.findByEmail(loginRequest.getEmail());
        if (userFromDB.isPresent() && encoder.matches(loginRequest.getPassword(), userFromDB.get().getPassword())){
            String token = this.jwtService.generateToken(userFromDB.get().getEmail(), userFromDB.get().getRole());
            return Optional.of(token);
        }

        return Optional.empty();
    }
}
