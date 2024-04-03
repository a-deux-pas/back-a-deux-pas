package adeuxpas.back.service;

import adeuxpas.back.dto.PreferredScheduleDTO;
import adeuxpas.back.dto.mapper.UserMapper;
import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.WeekDays;

import java.time.LocalTime;
import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


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
class UserServiceImplTest {

    // Create an instance of UserServiceImpl and automatically inject its dependencies
    @InjectMocks
    private UserServiceImpl userServiceImpl;

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
    void testGroupByTimesCorrectGrouping() {
        // Mocking User Preferred Schedules data
        User user = createUser(1L);

        // Mocking preferred schedules
        List<PreferredSchedule> preferredScheduleList = new ArrayList<>();
        PreferredSchedule preferredSchedule1 = new PreferredSchedule(WeekDays.LUNDI, LocalTime.of(18, 0), LocalTime.of(20, 0), user);
        preferredSchedule1.setId(2L);
        preferredScheduleList.add(preferredSchedule1);
        
        PreferredSchedule preferredSchedule2 = new PreferredSchedule(WeekDays.MARDI, LocalTime.of(18, 0), LocalTime.of(20, 0), user);
        preferredSchedule2.setId(3L);
        preferredScheduleList.add(preferredSchedule2);
        
        PreferredSchedule preferredSchedule3 = new PreferredSchedule(WeekDays.MERCREDI, LocalTime.of(8, 0), LocalTime.of(9, 0), user);
        preferredSchedule3.setId(4L);
        preferredScheduleList.add(preferredSchedule3);
        
        PreferredSchedule preferredSchedule4 = new PreferredSchedule(WeekDays.SAMEDI, LocalTime.of(12, 0), LocalTime.of(14, 0), user);
        preferredSchedule4.setId(5L);
        preferredScheduleList.add(preferredSchedule4);
        
        PreferredSchedule preferredSchedule5 = new PreferredSchedule(WeekDays.DIMANCHE, LocalTime.of(12, 0), LocalTime.of(14, 0), user);
        preferredSchedule5.setId(6L);
        preferredScheduleList.add(preferredSchedule5);

        // Mocking User Preferred Schedules DTO data
        List<PreferredScheduleDTO> preferredScheduleListDTO = new ArrayList<>();
        preferredScheduleList.forEach( schedule ->  {
            preferredScheduleListDTO.add(UserMapper.INSTANCE.mapPreferredScheduleToDTO(schedule));
        });

        // Call the method to test
        List<PreferredScheduleDTO> preferredSchedules = userServiceImpl.groupByTimes(preferredScheduleListDTO);
        
        // Assertions
        assertNotNull(preferredSchedules);
        assertFalse(preferredSchedules.isEmpty());
        assertEquals(3, preferredSchedules.size());
        assertEquals(2, preferredSchedules.get(0).getDaysOfWeek().size()); 
        assertEquals(1, preferredSchedules.get(1).getDaysOfWeek().size());
        assertEquals(2, preferredSchedules.get(2).getDaysOfWeek().size()); 
    }

    /**
     * Test case for grouping preferred schedules by times with an empty list.
     * <p>
     * It tests whether the method groupByTimes handles an empty list of preferred schedules correctly.
     * </p>
     */
    @Test
    void testGroupByTimesWithEmptyList() {   
        List<PreferredScheduleDTO> emptyList = new ArrayList<>();
        List<PreferredScheduleDTO> result = userServiceImpl.groupByTimes(emptyList);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
