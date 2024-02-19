package adeuxpas.back.service;

import adeuxpas.back.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);
}
