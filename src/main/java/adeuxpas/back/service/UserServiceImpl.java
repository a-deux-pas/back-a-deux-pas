package adeuxpas.back.service;

import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.ProfilePageUserDTO;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.PreferredMeetingPlaceRepository;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Implementation class for the UserService interface.
 * <p>
 * This service class provides implementations for user-related operations.
 * </p>
 * <p>
 * It interacts with the UserRepository to perform database operations related to users.
 * </p>
 *
 * @author Mircea Bardan
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PreferredScheduleRepository preferredScheduleRepository;
    private final PreferredMeetingPlaceRepository preferredMeetingPlaceRepository;
    private final UserMapper userMapper;

    /**
     * Constructor for UserServiceImpl.
     *
     * @param userRepository The UserRepository for interacting with user-related database operations.
     */
    public UserServiceImpl(
        @Autowired UserRepository userRepository, 
        @Autowired PreferredScheduleRepository preferredScheduleRepository,
        @Autowired PreferredMeetingPlaceRepository preferredMeetingPlaceRepository,
        @Autowired UserMapper userMapper
        ){
        this.userRepository = userRepository;
        this.preferredScheduleRepository = preferredScheduleRepository;
        this.preferredMeetingPlaceRepository = preferredMeetingPlaceRepository;
        this.userMapper = userMapper;
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
     * @return An optional containing the user if found, or an empty optional otherwise.
     */
    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves profile information of a user by user ID.
     *
     * This method retrieves the profile information of a user based on the provided user ID.
     * It maps the user entity to a DTO representing profile page user information.
     *
     * @param userId the ID of the user to retrieve profile information for.
     * @return the profile information of the user or an exception if the user is not found.
     */
    @Override
    public ProfilePageUserDTO findUserProfileInfoById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            ProfilePageUserDTO mappedUser = userMapper.mapUserToProfilePageUserDTO(user);
            return mappedUser;
        } else {
            throw new EntityNotFoundException("User with ID : " + userId + " not Found");
        }
    }

    /**
     * Retrieves preferred schedules of a user.
     *
     * This method retrieves all preferred schedules associated with a given user.
     * It performs a filtering operation to group preferred schedules by specific time.
     *
     * @param user the concerned user.
     * @return a list of preferred schedules grouped by specific time or an exception if the user is not found.
     */
    @Override
    public List<PreferredScheduleDTO> findPreferredSchedulesByUserId(Long userId) {
            Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<PreferredSchedule> preferredSchedules = preferredScheduleRepository.findPreferredSchedulesByUser(user);
            List<PreferredScheduleDTO> mappedPreferredSchedules = preferredSchedules.stream().map(userMapper::mapPreferredScheduleToDTO).collect(Collectors.toList());
            List<PreferredScheduleDTO> filteredPreferredSchedules = this.groupByTimes(mappedPreferredSchedules);
            return filteredPreferredSchedules;
        } else {
            throw new EntityNotFoundException("User with ID : " + userId + " not Found");
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
     * This method retrieves all preferred meeting places associated with a given user.
     * It maps the preferred meeting places to DTOs.
     *
     * @param user the concerned user.
     * @return a list of preferred meeting places or an exception if the user is not found.
     */
    @Override
    public List<PreferredMeetingPlaceDTO> findPreferredMeetingPlacesByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<PreferredMeetingPlace> PreferredMeetingPlaces = preferredMeetingPlaceRepository.findPreferredMeetingPlacesByUser(user);
            List<PreferredMeetingPlaceDTO> mappedPreferredMeetingPlaces = PreferredMeetingPlaces.stream().map(userMapper::mapPreferredMettingPlaceToDTO).collect(Collectors.toList());
            return mappedPreferredMeetingPlaces;
        } else {
            throw new EntityNotFoundException("User with ID : " + userId + " not Found");
        }
    }
}
