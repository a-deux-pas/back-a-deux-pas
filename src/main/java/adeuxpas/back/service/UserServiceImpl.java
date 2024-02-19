package adeuxpas.back.service;

import adeuxpas.back.entity.User;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
