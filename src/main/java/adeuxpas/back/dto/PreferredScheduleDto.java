package adeuxpas.back.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing preferred schedules.
 * <p>
 * This DTO encapsulates information about a user's preferred schedules,
 * including the days of the week, start time, end time, and user ID.
 * </p>
 * <p>
 * It is typically used to transfer preferred schedule data between different layers of the application,
 * such as between the controller and the service layer.
 * </p>
 * 
 *  @author Léa Hadida
 */
public class PreferredScheduleDTO {
    private Long id;
    private List<Integer> daysOfWeek;;
    private String startTime;
    private String endTime;
    private Long userId;

    /**
     * Default constructor for PreferredScheduleDTO.
     */
    public PreferredScheduleDTO() {
    }

    /**
     * Constructor for PreferredScheduleDTO with all attributes.
     *
     * @param id          The unique identifier for the preferred schedule.
     * @param daysOfWeek  The days of the week for the preferred schedule.
     * @param startTime   The start time of the preferred schedule.
     * @param endTime     The end time of the preferred schedule.
     * @param userId      The ID of the user associated with the preferred schedule.
     */
    public PreferredScheduleDTO(Long id, List<Integer> daysOfWeek, LocalTime startTime, LocalTime endTime, Long userId) {
        this.id = id;
        this.daysOfWeek = daysOfWeek;
        this.startTime = formatTime(startTime);
        this.endTime = formatTime(endTime);
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<Integer> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<Integer> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    /**
     * Formats a LocalTime object to a String representation in "HH:mm" format.
     *
     * @param time The LocalTime object to be formatted.
     * @return The formatted time string.
     */
    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
