package adeuxpas.back.entity;

import adeuxpas.back.enums.EventNames;
import jakarta.persistence.*;

/**
 * Entity representing a user's notifications preferences.
 * 
 * Persisted in the database and interacts with NotificationRepository.
 *
 * @author Léa Hadida
 */
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_name", nullable = false)
    private EventNames eventName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Notification() {
    }

    public Notification(EventNames eventName, User user) {
        this.eventName = eventName;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EventNames getEventName() {
        return eventName;
    }

    public void setEventName(EventNames eventName) {
        this.eventName = eventName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
