package adeuxpas.back.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Data Transfer Object (DTO) for representing preferred schedule.
 * <p>
 * This DTO encapsulates information about a user's preferred schedule,
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
    private List<Integer> daysOfWeek;
    private String startTime;
    private String endTime;
    private Long userId;

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
}

