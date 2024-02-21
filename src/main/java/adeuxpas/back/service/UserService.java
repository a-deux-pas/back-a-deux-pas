package adeuxpas.back.service;

import adeuxpas.back.entity.User;
import java.util.Optional;

/**
 * Interface defining user-related operations for the application.
 * <p>
 * This service interface declares contracts for user-related operations that must be respected by all implementing classes.
 * </p>
 *
 * @author Mircea Bardan
 */
public interface UserService {

    void save(User user);
    Optional<User> findUserByEmail(String email);
}
