package adeuxpas.back.service;

import adeuxpas.back.dto.LoginRequest;
import adeuxpas.back.dto.SignupRequest;
import adeuxpas.back.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);
    boolean canDoSignup(SignupRequest signupRequest);

    Optional<String> login(LoginRequest loginRequest);
}