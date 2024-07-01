package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MeetingDisplayDTO {

    private LocalDateTime date;
    private String buyerAlias;
    private String buyerProfilePictureUrl;
    private LocalDateTime inscriptionDate;
    private String meetingPlaceName;
    private String postalCode;
    private String city;
    private String street;
    private String adTitle;
    private BigDecimal adPrice;
    private String adPictureUrl;

    // Getters and setters
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getBuyerAlias() {
        return buyerAlias;
    }

    public void setBuyerAlias(String buyerAlias) {
        this.buyerAlias = buyerAlias;
    }

    public String getBuyerProfilePictureUrl() {
        return buyerProfilePictureUrl;
    }

    public void setBuyerProfilePictureUrl(String buyerProfilePictureUrl) {
        this.buyerProfilePictureUrl = buyerProfilePictureUrl;
    }

    public LocalDateTime getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(LocalDateTime inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getMeetingPlaceName() {
        return meetingPlaceName;
    }

    public void setMeetingPlaceName(String meetingPlaceName) {
        this.meetingPlaceName = meetingPlaceName;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdTitle() {
        return adTitle;
    }

    public void setAdTitle(String adTitle) {
        this.adTitle = adTitle;
    }

    public BigDecimal getAdPrice() {
        return adPrice;
    }

    public void setAdPrice(BigDecimal adPrice) {
        this.adPrice = adPrice;
    }

    public String getAdPictureUrl() {
        return adPictureUrl;
    }

    public void setAdPictureUrl(String adPictureUrl) {
        this.adPictureUrl = adPictureUrl;
    }
}
