package adeuxpas.back.dto.user;

import java.util.List;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) for representing user profile information received
 * from the front.
 * <p>
 * This DTO encapsulates various attributes of a user's profile, such as alias,
 * profile picture URL, biography, location details.
 * </p>
 * <p>
 * It is typically used to transfer user profile data between different layers
 * of the application, such as between the controller and the service layer
 * before it is persisted to the database.
 * </p>
 * 
 * @author Léa Hadida
 */
public class UserProfileRequestDTO {

    @NotBlank
    private String id;
    @NotBlank
    @Size(min = 3, max = 30)
    private String alias;
    @Size(min = 10, max = 600)
    private String bio;
    @NotBlank
    private String street;
    @NotBlank
    @Size(min = 5, max = 5)
    private String postalCode;
    @NotBlank
    private String city;
    private String country = "France";
    @NotBlank
    private String bankAccountTokenId;
    @NotEmpty
    @Size(min = 1, max = 5)
    private List<PreferredMeetingPlaceDTO> preferredMeetingPlaces;
    @NotEmpty
    @Size(min = 1)
    private List<PreferredScheduleDTO> preferredSchedules;
    private List<NotificationDTO> notifications;

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBankAccountTokenId() {
        return bankAccountTokenId;
    }

    public void setBankAccountTokenId(String bankAccountTokenId) {
        this.bankAccountTokenId = bankAccountTokenId;
    }

    public List<PreferredMeetingPlaceDTO> getPreferredMeetingPlaces() {
        return preferredMeetingPlaces;
    }

    public void setPreferredMeetingPlaces(List<PreferredMeetingPlaceDTO> preferredMeetingPlaces) {
        this.preferredMeetingPlaces = preferredMeetingPlaces;
    }

    public List<PreferredScheduleDTO> getPreferredSchedules() {
        return preferredSchedules;
    }

    public void setPreferredSchedules(List<PreferredScheduleDTO> preferredSchedules) {
        this.preferredSchedules = preferredSchedules;
    }

    public List<NotificationDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<NotificationDTO> notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "UserProfileRequestDTO{" +
                "id='" + id + '\'' +
                ", alias='" + alias + '\'' +
                ", bio='" + bio + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
