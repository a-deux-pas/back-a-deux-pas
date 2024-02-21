package adeuxpas.back.service;

import adeuxpas.back.entity.User;

import java.util.Optional;

public interface UserService {

    void save(User user);
    Optional<User> findUserByEmail(String email);
}
