package adeuxpas.back.entity;

import java.time.LocalTime;
import adeuxpas.back.enums.WeekDays;
import jakarta.persistence.*;

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
    @Column(name = "week_day", nullable = false)
    private WeekDays weekDay;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
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
    public PreferredSchedule(WeekDays weekDay, LocalTime startTime, LocalTime endTime, User user) {
        this.weekDay = weekDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
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
