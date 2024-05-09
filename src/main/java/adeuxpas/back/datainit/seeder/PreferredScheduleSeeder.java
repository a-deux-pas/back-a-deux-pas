package adeuxpas.back.datainit.seeder;

import adeuxpas.back.entity.PreferredSchedule;
import adeuxpas.back.entity.User;
import adeuxpas.back.enums.WeekDays;
import adeuxpas.back.repository.PreferredScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Seeder class responsible for generating random preferred schedules for users.
 */
@Component
public class PreferredScheduleSeeder {

    private final PreferredScheduleRepository preferredScheduleRepository;

    private final SecureRandom random = new SecureRandom();

    /**
     * Constructs a new PreferredScheduleSeeder with the specified PreferredScheduleRepository.
     * @param preferredScheduleRepository The repository for preferred schedules.
     */
    public PreferredScheduleSeeder(@Autowired PreferredScheduleRepository preferredScheduleRepository){
        this.preferredScheduleRepository = preferredScheduleRepository;
    }

    /**
     * Generates random schedules for a given user.
     * @param user The user for whom schedules are generated.
     */
    public void generateSchedulesForUser(User user) {
        for (int i = 0; i < 6; i++) {
            // Generate a new set of schedules until there are no overlaps
            Set<PreferredSchedule> newSchedules;
            do {
                newSchedules = generateRandomScheduleForUser(user);
            } while (isScheduleOverlapping(user, newSchedules));
            for (PreferredSchedule schedule : newSchedules) {
                schedule.setUser(user);
                this.preferredScheduleRepository.save(schedule);
            }
        }
    }

    /**
     * Checks if the new schedules overlap with existing schedules for a user.
     * @param user The user for whom schedules are checked.
     * @param newSchedules The new schedules to check for overlap.
     * @return true if there is an overlap, false otherwise.
     */
    private boolean isScheduleOverlapping(User user, Set<PreferredSchedule> newSchedules) {
        List<PreferredSchedule> existingSchedules = this.preferredScheduleRepository.findPreferredSchedulesByUser(user);
        return existingSchedules.stream()
                .flatMap(existingSchedule -> newSchedules.stream()
                        .filter(newSchedule -> existingSchedule.getWeekDay() == newSchedule.getWeekDay() &&
                                existingSchedule.getEndTime().isAfter(newSchedule.getStartTime()) &&
                                existingSchedule.getStartTime().isBefore(newSchedule.getEndTime())))
                .findFirst()
                .isPresent();
    }

    /**
     * Generates a set of random schedules.
     * @return A set of random schedules.
     */
    private Set<PreferredSchedule> generateRandomScheduleForUser(User user) {
        Set<PreferredSchedule> schedules = new HashSet<>();
        WeekDays randomDay = WeekDays.values()[random.nextInt(WeekDays.values().length)];
        LocalTime startTime = generateRandomTime();
        LocalTime endTime = generateRandomEndTime(startTime);
        PreferredSchedule schedule = new PreferredSchedule(randomDay, startTime, endTime, user);
        schedules.add(schedule);
        return schedules;
    }

    /**
     * Generates a random time between 8 AM and 9 PM.
     * @return A random time between 8 AM and 9 PM.
     */
    private LocalTime generateRandomTime() {
        // Generate a random hour between 8 and 21
        int hour = random.nextInt(13) + 8;
        return LocalTime.of(hour, 0);
    }

    /**
     * Generates a random end time based on the start time.
     * @param startTime The start time of the schedule.
     * @return A random end time based on the start time.
     */
    private LocalTime generateRandomEndTime(LocalTime startTime) {
        // Generate a random hour between the start time and 12 hours later
        int hour = startTime.getHour() + random.nextInt(12) + 1;
        // Ensure end time doesn't exceed 10:00 PM (22:00)
        hour = Math.min(hour, 22);
        return LocalTime.of(hour, 0);
    }
}
