package adeuxpas.back.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class PreferredScheduleDTO {
    private Long id;
    private List<Integer> daysOfWeek;;
    private String startTime;
    private String endTime;
    private Long userId;

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
    
    // Méthode de formatage des horaires
    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
