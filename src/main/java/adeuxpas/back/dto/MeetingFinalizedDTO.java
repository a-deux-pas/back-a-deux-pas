package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MeetingFinalizedDTO {

    private Long idMeeting;
    private LocalDateTime date;
    private String status;
    private String buyerAlias;
    private String sellerAlias;
    private Long buyerId;
    private Long sellerId;
    private String buyerProfilePictureUrl;
    private String sellerProfilePictureUrl;
    private String buyerInscriptionDate;
    private String sellerInscriptionDate;
    private String meetingPlaceName;
    private String postalCode;
    private String city;
    private String street;
    private String adTitle;
    private BigDecimal adPrice;
    private String adPictureUrl;

    public Long getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(Long idMeeting) {
        this.idMeeting = idMeeting;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBuyerAlias() {
        return buyerAlias;
    }

    public void setBuyerAlias(String buyerAlias) {
        this.buyerAlias = buyerAlias;
    }

    public String getSellerAlias() {
        return sellerAlias;
    }

    public void setSellerAlias(String sellerAlias) {
        this.sellerAlias = sellerAlias;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerProfilePictureUrl() {
        return buyerProfilePictureUrl;
    }

    public void setBuyerProfilePictureUrl(String buyerProfilePictureUrl) {
        this.buyerProfilePictureUrl = buyerProfilePictureUrl;
    }

    public String getSellerProfilePictureUrl() {
        return sellerProfilePictureUrl;
    }

    public void setSellerProfilePictureUrl(String sellerProfilePictureUrl) {
        this.sellerProfilePictureUrl = sellerProfilePictureUrl;
    }

    public String getBuyerInscriptionDate() {
        return buyerInscriptionDate;
    }

    public void setBuyerInscriptionDate(String buyerInscriptionDate) {
        this.buyerInscriptionDate = buyerInscriptionDate;
    }

    public String getSellerInscriptionDate() {
        return sellerInscriptionDate;
    }

    public void setSellerInscriptionDate(String sellerInscriptionDate) {
        this.sellerInscriptionDate = sellerInscriptionDate;
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
