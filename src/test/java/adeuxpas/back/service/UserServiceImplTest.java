package adeuxpas.back.service;

import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.WeekDays;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test class for UserServiceImpl.
 * <p>
 * This class tests the functionalities of the UserServiceImpl class.
 * It uses Mockito for mocking dependencies and SpringBootTest for integration testing.
 * </p>
 * 
 * @author Léa Hadida
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServiceImplTest {

    // Create the instance of UserSerive and automatically inject the UserRepository mock
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserMapper userMapper;

    /**
     * Creates a user entity with the given user ID.
     *
     * @param userId The ID of the user.
     * @return The user entity.
     */
    private User createUser(Long userId) {
        User user = new User();
        user.setId(userId);
        // add other properties here if needed
        return user;
    }

    /**
     * Test case for grouping preferred schedules by times.
     * <p>
     * It tests whether the method groupByTimes correctly groups preferred schedules 
     * based on their time intervals.
     * </p>
     */
    @Test
    public void testGroupByTimesCorrectGrouping() {
        // Mocking User Preferred Schedules data
        User user = createUser(1L);
        List<PreferredSchedule> preferredScheduleList = new ArrayList<>();
        
        // Creating and adding preferred schedules with different days and times
        PreferredSchedule preferredSchedule1 = new PreferredSchedule(WeekDays.LUNDI, LocalTime.of(18, 0), LocalTime.of(20, 0));
        preferredSchedule1.setId(2L);
        preferredSchedule1.setUser(user);
        preferredScheduleList.add(preferredSchedule1);
        
        PreferredSchedule preferredSchedule2 = new PreferredSchedule(WeekDays.MARDI, LocalTime.of(18, 0), LocalTime.of(20, 0));
        preferredSchedule2.setId(3L);
        preferredSchedule2.setUser(user);
        preferredScheduleList.add(preferredSchedule2);
        
        PreferredSchedule preferredSchedule3 = new PreferredSchedule(WeekDays.MERCREDI, LocalTime.of(8, 0), LocalTime.of(9, 0));
        preferredSchedule3.setId(4L);
        preferredSchedule3.setUser(user);
        preferredScheduleList.add(preferredSchedule3);
        
        PreferredSchedule preferredSchedule4 = new PreferredSchedule(WeekDays.SAMEDI, LocalTime.of(12, 0), LocalTime.of(14, 0));
        preferredSchedule4.setId(5L);
        preferredSchedule4.setUser(user);
        preferredScheduleList.add(preferredSchedule4);
        
        PreferredSchedule preferredSchedule5 = new PreferredSchedule(WeekDays.DIMANCHE, LocalTime.of(12, 0), LocalTime.of(14, 0));
        preferredSchedule5.setId(6L);
        preferredSchedule5.setUser(user);
        preferredScheduleList.add(preferredSchedule5);
        
        // Mocking User Preferred Schedules DTO data
        List<PreferredScheduleDTO> preferredScheduleListDTO = preferredScheduleList.stream().map(userMapper::mapPreferredScheduleToDTO).collect(Collectors.toList());

        // Call the method to test
        List<PreferredScheduleDTO> preferredSchedules = userServiceImpl.groupByTimes(preferredScheduleListDTO);
        
        // Assertions
        assertNotNull(preferredSchedules);
        assertFalse(preferredSchedules.isEmpty());
        assertEquals(preferredSchedules.size(), 3); 
        assertEquals(preferredSchedules.get(0).getDaysOfWeek().size(), 2); 
        assertEquals(preferredSchedules.get(1).getDaysOfWeek().size(), 1);
        assertEquals(preferredSchedules.get(2).getDaysOfWeek().size(), 2); 
    }

    /**
     * Test case for grouping preferred schedules by times with an empty list.
     * <p>
     * It tests whether the method groupByTimes handles an empty list of preferred schedules correctly.
     * </p>
     */
    @Test
    public void testGroupByTimesWithEmptyList() {   
        List<PreferredScheduleDTO> emptyList = new ArrayList<>();
        List<PreferredScheduleDTO> result = userServiceImpl.groupByTimes(emptyList);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}