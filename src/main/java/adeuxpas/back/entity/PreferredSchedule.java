package adeuxpas.back.entity;

import java.time.LocalTime;

import adeuxpas.back.enums.WeekDays;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entity representing a user's preferred schedule.
 * Contains information about preferred schedules, including days of the week
 * and time intervals.
 * Persisted in the database and interacts with PreferredScheduleRepository.
 *
 * @author Léa Hadida
 */
@Entity
public class PreferredSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "week_day")
    private WeekDays weekDay;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Default constructor for PreferredSchedule.
     */
    public PreferredSchedule() {
    }

    /**
     * Constructor for PreferredSchedule with all attributes except ID.
     *
     * @param weekDay   The day of the week for the preferred schedule.
     * @param startTime The start time of the preferred schedule.
     * @param endTime   The end time of the preferred schedule.
     */
    public PreferredSchedule(WeekDays weekDay, LocalTime startTime, LocalTime endTime) {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WeekDays getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDays weekDay) {
        this.weekDay = weekDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
