package adeuxpas.back.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfilePageUserDTO {
    private Long id;
    private String alias;
    private String bio;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String profilePicture;
    private String inscriptionDate;
    
    public ProfilePageUserDTO(Long id, String alias, String bio, String country, String city, String street,
            String postalCode, String profilePicture, LocalDate inscriptionDate) {
        this.id = id;
        this.alias = alias;
        this.bio = bio;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.profilePicture = profilePicture;
        this.inscriptionDate = formatTime(inscriptionDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(String inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    private String formatTime(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}