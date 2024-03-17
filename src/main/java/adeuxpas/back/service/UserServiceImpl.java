package adeuxpas.back.service;

import adeuxpas.back.dto.PreferredMeetingPlaceDTO;
import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.PreferredMeetingPlace;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.repository.PreferredMeetingPlaceRepository;
import adeuxpas.back.repository.PreferredScheduleRepository;
import adeuxpas.back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private final PreferredScheduleRepository preferredSchedulerRepository;
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
        this.preferredSchedulerRepository = preferredScheduleRepository;
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
     * Finds preferred schedules of an user.
     *
     * @param user concerned.
     * @return a list of preferred schedules.
     */
    @Override
    public List<PreferredScheduleDTO> findAllPreferredSchedulesByUser(User user) {
        List<PreferredSchedule> preferredSchedules = preferredSchedulerRepository.findAllPreferredSchedulesByUser(user);
        List<PreferredScheduleDTO> mappedPreferredSchedules = preferredSchedules.stream().map(userMapper::mapPreferredScheduleToDTO).collect(Collectors.toList());
        List<PreferredScheduleDTO> filteredPreferredSchedules = this.groupByTimes(mappedPreferredSchedules);
        return filteredPreferredSchedules;
    }

    /**
     * Groups a list of PreferredScheduleDTO objects time.
     * 
     * @param preferredSchedules The list of PreferredScheduleDTO objects to group.
     * @return A list of PreferredScheduleDTO objects grouped by time.
     */
    public List<PreferredScheduleDTO> groupByTimes(List<PreferredScheduleDTO> preferredSchedules) {
    List<PreferredScheduleDTO> result = new ArrayList<>();
    // Grouping by start time and end time
    Map<String, Map<String, List<PreferredScheduleDTO>>> groupedByTime = preferredSchedules.stream()
            .collect(Collectors.groupingBy(PreferredScheduleDTO::getStartTime,
                    Collectors.groupingBy(PreferredScheduleDTO::getEndTime)));
    // Iterating through the groups
    groupedByTime.forEach((startTime, endTimeMap) -> {
        endTimeMap.forEach((endTime, schedules) -> {
            // Retrieving the days of the week as a list of integers
            List<Integer> daysOfWeek = schedules.stream()
                .flatMap(dto -> dto.getDaysOfWeek().stream()) 
                .collect(Collectors.toList()); 
            Long id = schedules.get(0).getId();
            Long userId = schedules.get(0).getUserId();

            // Creating the new PreferredScheduleDto and adding it to the result list
            result.add(new PreferredScheduleDTO(id, daysOfWeek, LocalTime.parse(startTime), LocalTime.parse(endTime), userId));
        });
    });
        return result;
    }

    /**
     * Finds preferred meeting places of an user.
     *
     * @param user concerned.
     * @return a list of preferred meeting places.
     */
    @Override
    public List<PreferredMeetingPlaceDTO> findAllPreferredMeetingPlacesByUser(User user) {
        List<PreferredMeetingPlace> PreferredMeetingPlaces = preferredMeetingPlaceRepository.findAllPreferredMeetingPlacesByUser(user);
        List<PreferredMeetingPlaceDTO> mappedPreferredMeetingPlaces = PreferredMeetingPlaces.stream().map(userMapper::mapPreferredMettingPlaceToDTO).collect(Collectors.toList());
        return mappedPreferredMeetingPlaces;
    }
}
