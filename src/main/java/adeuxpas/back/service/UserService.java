package adeuxpas.back.service;

import adeuxpas.back.dto.CityAndPostalCodeDTO;
import adeuxpas.back.entity.User;
import java.util.Optional;
import java.util.Set;

/**
 * Interface defining user-related operations for the application.
 * <p>
 * This service interface declares contracts for user-related operations that must be respected by all implementing classes.
 * </p>
 *
 * @author Mircea Bardan
 */
public interface UserService {

    /**
     * Abstract method that attempts to save the user to the database.
     * @param user The {@code User} to be saved.
     */
    void save(User user);

    /**
     * Abstract method that attempts to find the user by its email.
     * @param email The email address used in the search.
     */
    Optional<User> findUserByEmail(String email);

    Set<CityAndPostalCodeDTO> getUniqueCitiesAndPostalCodes();
}
