package adeuxpas.back.service;

import adeuxpas.back.dto.CityAndPostalCodeResponseDTO;
import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.ProfilePageUserDTO;
import adeuxpas.back.entity.User;

import java.util.List;
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

    /**
     * Abstract method that attempts to find the user info with its ID.
     * @param userId The user ID used in the search.
     */
    ProfilePageUserDTO findUserProfileInfoById(Long userId);

    /**
     * Abstract method that attempts to find preferred schedule of an user.
     * @param userId the user's ID
     */
    List<PreferredScheduleDTO> findPreferredSchedulesByUserId(Long userId);

    /**
     * Abstract method that attempts to find preferred meeting places of an user.
     * @param userId the user's ID
     */
    List<PreferredMeetingPlaceDTO> findPreferredMeetingPlacesByUserId(Long userId);

    Set<CityAndPostalCodeResponseDTO> getUniqueCitiesAndPostalCodes();
}
