package adeuxpas.back.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object (DTO) for representing notification.
 * <p>
 * This DTO encapsulates information about a user's notifications preferences,
 * </p>
 * <p>
 * It is typically used to transfer notification data between
 * different layers of the application,
 * such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class NotificationDTO {
    @NotBlank
    private String eventName;
    @NotBlank
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}
