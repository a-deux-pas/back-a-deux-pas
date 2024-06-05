package adeuxpas.back.dto;

import java.util.List;

import jakarta.validation.constraints.*;

/*
 * This a data transfer object (DTO) that is used when receiving 
 * a user profile object from the front 
 * to transform it into a ProfilePostDto during
 * the profile creation process in order 
 * to save it into the database
 */

public class UserProfileRequestDTO {

    @NotNull
    private String userId;
    @NotNull // TO DO: à supprimer une fois la première partie du signup réalisé
    private String email;
    @NotNull // TO DO: à supprimer une fois la première partie du signup réalisé
    private String password;
    @Size(min = 3)
    @NotNull
    private String alias;
    @Size(min = 10)
    private String bio;
    @NotNull
    private String street;
    @Size(max = 5)
    @NotNull
    private String postalCode;
    @NotNull
    private String city;
    @NotNull
    private String bankAccountHolder;
    @NotNull
    private String bankAccountNumber;
    @NotEmpty
    @Size(min = 1, max = 5)
    private List<PreferredMeetingPlaceDTO> preferredMeetingPlaces;
    @NotEmpty
    @Size(min = 1)
    private List<PreferredScheduleDTO> preferredSchedules;
    private List<NotificationDTO> notifications;

    // getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getBankAccountHolder() {
        return bankAccountHolder;
    }

    public void setBankAccountHolder(String bankAccountHolde) {
        this.bankAccountHolder = bankAccountHolde;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
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
}
