package adeuxpas.back.service;

import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.UserAliasAndLocationResponseDTO;
import adeuxpas.back.dto.UserProfileResponseDTO;
import adeuxpas.back.dto.UserProfileRequestDTO;
import adeuxpas.back.entity.User;

import java.util.*;

/**
 * Interface defining user-related operations for the application.
 * <p>
 * This service interface declares contracts for user-related operations that
 * must be respected by all implementing classes.
 * </p>
 *
 * @author Mircea Bardan
 */
public interface UserService {

    /**
     * Abstract method that attempts to save the user to the database.
     * 
     * @param user The {@code User} to be saved.
     */
    void save(User user);

    /**
     * Abstract method that attempts to find the user by its email.
     * 
     * @param email The email address used in the search.
     * @return An optional containing the user if found.
     */
    Optional<User> findUserByEmail(String email);

    /**
     * Abstract method that attempts to find the user by its alias.
     * 
     * @param alias The alias used in the search.
     * @return An optional containing the user if found.
     */
    Optional<User> findUserByAlias(String alias);

    /**
     * Abstract method that attempts to create a user profile.
     * 
     * @param profileDto The DTO containing the details of an user Profile.
     */
    void createProfile(UserProfileRequestDTO profileDto);

    /**
     * Abstract method that attempts to find the user info with its alias.
     * 
     * @param userAlias The user's alias.
     * @return the profile information of the user.
     */
    UserProfileResponseDTO findUserProfileInfoByAlias(String userAlias);

    /**
     * Abstract method that attempts to find preferred schedule of an user.
     * 
     * @param userId the user's ID.
     * @return a list of preferred schedules.
     */
    List<PreferredScheduleDTO> findPreferredSchedulesByUserId(long userId);

    /**
     * Abstract method that attempts to find preferred meeting places of an user.
     * 
     * @param userId the user's ID.
     * @return a list of preferred meeting places.
     */
    List<PreferredMeetingPlaceDTO> findPreferredMeetingPlacesByUserId(long userId);

    /**
     * Abstract method that attempts to find city and postal code.
     * 
     * @return a set of CityAndPostalCodeResponseDTOs.
     */
    Set<UserAliasAndLocationResponseDTO> getUniqueCitiesAndPostalCodes();

    /**
     * Abstract method that attempts to find the user's alias, city and postal code.
     * 
     * @param userId the user's ID.
     * @return a UserAliasAndLocationResponseDTO.
     */
    UserAliasAndLocationResponseDTO getUserAliasAndLocation(long userId);

    /**
     * Abstract method that attempts to find the sellers which have the same postal
     * code as the user.
     * 
     * @param userId the user's ID.
     * @return a list of UserProfileResponseDTO.
     */
    List<UserProfileResponseDTO> getSellersNearby(long userId);
}
