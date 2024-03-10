package adeuxpas.back.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PreferredScheduleDto {
    private Long id;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Long userId;

    public PreferredScheduleDto(Long id, String dayOfWeek, LocalTime startTime, LocalTime endTime, Long userId) {
        this.id = id;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        this.endTime = endTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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
