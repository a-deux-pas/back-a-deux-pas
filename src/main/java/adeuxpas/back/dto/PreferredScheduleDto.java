package adeuxpas.back.dto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PreferredScheduleDto {
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    public PreferredScheduleDto(String dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
        this.endTime = endTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
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
}
