package adeuxpas.back.service;

import adeuxpas.back.dto.*;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.PreferredMeetingPlaceRepository;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.io.UncheckedIOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.Collectors;
import java.util.*;

/**
 * Implementation class for the UserService interface.
 * <p>
 * This service class provides implementations for user-related operations.
 * </p>
 * <p>
 * It interacts with the UserRepository to perform database operations related
 * to users.
 * </p>
 *
 * @author Mircea Bardan
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PreferredScheduleRepository preferredScheduleRepository;
    private final PreferredMeetingPlaceRepository preferredMeetingPlaceRepository;
    private final UserMapper userMapper;
    private final CloudinaryService cloudinaryService;

    private static final String USER_NOT_FOUND_MESSAGE = "User with ID : %d not Found";

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepository The UserRepository for interacting with user-related
     *                       database operations.
     */
    public UserServiceImpl(
            @Autowired UserRepository userRepository,
            @Autowired PreferredScheduleRepository preferredScheduleRepository,
            @Autowired PreferredMeetingPlaceRepository preferredMeetingPlaceRepository,
            @Autowired CloudinaryService cloudinaryService,
            @Autowired UserMapper userMapper) {
        this.userRepository = userRepository;
        this.preferredScheduleRepository = preferredScheduleRepository;
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
        this.userMapper = userMapper;
        this.cloudinaryService = cloudinaryService;
    }

    /**
     * Saves a user to the database.
     *
     * @param user The user to save.
     */
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    /**
     * Finds a user by their email address.
     *
     * @param email The email address of the user to find.
     * @return An optional containing the user if found, or an empty optional
     *         otherwise.
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Finds a user by their alias.
     *
     * @param alias The alias of the user to find.
     * @return An optional containing the user if found, or an empty optional
     *         otherwise.
     */
    @Override
    public Optional<User> findUserByAlias(String alias) {
        return userRepository.findByAlias(alias);
    }

    /**
     * Saves a user profile and preferences.
     *
     * @param profileDto the profile dto to save.
     */
    @Override
    public void createProfile(UserProfileRequestDTO profileDto, MultipartFile profilePicture) {
        Long userId = Long.parseLong(profileDto.getId());
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User alias = findUserByAlias(profileDto.getAlias()).orElse(null);
            if (alias != null) {
                throw new IllegalArgumentException(
                        "A user with alias '" + profileDto.getAlias() + "' already exists");
            }
            String publicId = "profilePicture-" + profileDto.getAlias();
            if (profilePicture != null) {
                try {
                    Map<String, Object> profilePictureObject = cloudinaryService
                            .upload(publicId, profilePicture);
                    String profilePictureUrl = (String) profilePictureObject.get("url");
                    user.setProfilePicture(profilePictureUrl);
                } catch (IOException e) {
                    throw new UncheckedIOException("Failed to upload profile picture", e);
                }
            }
            userMapper.mapProfileUserToUser(profileDto, user);
            userRepository.save(user);
        } else {
            throw new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
    }

    /**
     * Retrieves profile information of a user by user ID.
     *
     * This method retrieves the profile information of a user based on the provided
     * user ID.
     * It maps the user entity to a DTO representing profile page user information.
     *
     * @param userAlias the alias of the user to retrieve profile information.
     * @return the profile information of the user or an exception if the user is
     *         not found.
     */
    @Override
    public UserProfileResponseDTO findUserProfileInfoByAlias(String userAlias) {
        Optional<User> optionalUser = this.findUserByAlias(userAlias);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return userMapper.mapUserToUserProfileResponseDTO(user);
        } else {
            throw new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userAlias));
        }
    }

    /**
     * Retrieves preferred schedules of a user.
     *
     * This method retrieves all preferred schedules associated with a given user.
     * It performs a filtering operation to group preferred schedules by specific
     * time.
     *
     * @param userId the concerned user's ID.
     * @return a list of preferred schedules grouped by specific time or an
     *         exception if the user is not found.
     */
    @Override
    public List<PreferredScheduleDTO> findPreferredSchedulesByUserId(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<PreferredSchedule> preferredSchedules = preferredScheduleRepository.findPreferredSchedulesByUser(user);
            List<PreferredScheduleDTO> mappedPreferredSchedules = preferredSchedules.stream()
                    .map(userMapper::mapPreferredScheduleToDTO).toList();
            return this.groupByTimes(mappedPreferredSchedules);
        } else {
            throw new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
    }

    /**
     * Groups a list of PreferredScheduleDTO objects time.
     *
     * @param preferredSchedules The list of PreferredScheduleDTO objects to group.
     * @return A list of PreferredScheduleDTO objects grouped by time.
     */
    public List<PreferredScheduleDTO> groupByTimes(List<PreferredScheduleDTO> preferredSchedules) {
        Map<String, PreferredScheduleDTO> resultMap = new HashMap<>();

        // Iterating through the list of PreferredScheduleDTOs
        for (PreferredScheduleDTO dto : preferredSchedules) {
            // Creating a key based on the user ID, start time, and end time
            Long userId = dto.getUserId();
            String key = userId + "-" + dto.getStartTime() + "-" + dto.getEndTime();

            // Retrieving the existing schedule for this key (if any)
            PreferredScheduleDTO existingSchedule = resultMap.get(key);

            if (existingSchedule == null) {
                // If no existing schedule, create a new one with the same properties
                PreferredScheduleDTO newSchedule = new PreferredScheduleDTO();
                newSchedule.setId(dto.getId());
                newSchedule.setUserId(userId);
                newSchedule.setStartTime(dto.getStartTime());
                newSchedule.setEndTime(dto.getEndTime());
                newSchedule.setDaysOfWeek(new ArrayList<>(dto.getDaysOfWeek()));
                resultMap.put(key, newSchedule); // Use the constructed key
            } else {
                // If an existing schedule exists, merge the days of the week
                existingSchedule.getDaysOfWeek().addAll(dto.getDaysOfWeek());
            }
        }
        // Convert the map values to a list and return
        return new ArrayList<>(resultMap.values());
    }

    /**
     * Retrieves preferred meeting places of a user.
     *
     * This method retrieves all preferred meeting places associated with a given
     * user.
     * It maps the preferred meeting places to DTOs.
     *
     * @param userId the concerned user's ID.
     * @return a list of preferred meeting places or an exception if the user is not
     *         found.
     */
    @Override
    public List<PreferredMeetingPlaceDTO> findPreferredMeetingPlacesByUserId(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<PreferredMeetingPlace> preferredMeetingPlaces = preferredMeetingPlaceRepository
                    .findPreferredMeetingPlacesByUser(user);
            return preferredMeetingPlaces.stream().map(userMapper::mapPreferredMeetingPlaceToDTO).toList();
        } else {
            throw new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
    }

    /**
     * Retrieves the users' unique postal codes along with their cities.
     *
     * This method retrieves all the users from the DB.
     * It then maps them to CityAndPostalCodeResponseDTO objects.
     * 
     * @return a set of CityAndPostalCodeResponseDTOs.
     */
    @Override
    public Set<UserAliasAndLocationResponseDTO> getUniqueCitiesAndPostalCodes() {
        List<User> users = this.userRepository.findAll();
        return users.stream()
                .filter(user -> user.getPostalCode() != null || user.getCity() != null)
                .map(userMapper::userToAliasAndLocationDTO)
                .collect(Collectors.toSet());
    }

    /**
     * Retrieves the user's alias, city and postal code.
     * 
     * @param userId the user's ID.
     * @return a UserAliasAndLocationResponseDTO.
     */
    @Override
    public UserAliasAndLocationResponseDTO getUserAliasAndLocation(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        if (optionalUser.isPresent()) {
            boolean isExistingLocationWithAds = userRepository
                    .existsUsersWithAdsByPostalCode(user.getPostalCode());
            UserAliasAndLocationResponseDTO dto = userMapper.userToAliasAndLocationDTO(user);
            dto.setIsExistingLocationWithAds(isExistingLocationWithAds);
            return dto;
        } else {
            throw new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
    }

    /**
     * Retrieves the sellers which have the same postal code as the user.
     * 
     * @param userId the user's ID.
     * @return a list of UserProfileResponseDTO.
     */
    @Override
    public List<UserProfileResponseDTO> getSellersNearby(long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // TO DO: à modifier pour trier par nombre de transactions une fois la table
            // implémenté
            List<User> sellersNearby = this.userRepository.findFirst5ByPostalCode(user.getPostalCode(), user.getId());
            return sellersNearby.stream().map(userMapper::mapUserToUserProfileResponseDTO).toList();
        } else {
            throw new EntityNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, userId));
        }
    }

    @Override
    public SellerCheckoutDTO findCheckoutSellerInfoByAlias(String alias) {
        Optional<User> optionalUser = this.findUserByAlias(alias);
        if (optionalUser.isPresent()){
            SellerCheckoutDTO sellerCheckoutDTO = new SellerCheckoutDTO();
            List<PreferredMeetingPlaceDTO> preferredMeetingPlaceDTOS = optionalUser.get()
                    .getPreferredMeetingPlaces().stream().map(userMapper::mapPreferredMeetingPlaceToDTO).toList();
            List<PreferredScheduleDTO> preferredScheduleDTOS = optionalUser.get()
                    .getPreferredSchedules().stream().map(userMapper::mapPreferredScheduleToDTO).toList();
            sellerCheckoutDTO.setPreferredMeetingPlaces(preferredMeetingPlaceDTOS);
            sellerCheckoutDTO.setPreferredSchedules(preferredScheduleDTOS);
            sellerCheckoutDTO.setId(optionalUser.get().getId());
            sellerCheckoutDTO.setBankAccountTokenId(optionalUser.get().getBankAccountTokenId());

            return sellerCheckoutDTO;
        }
        return null;
    }
}
