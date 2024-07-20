package adeuxpas.back.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MeetingToConfirmDTO {
    private Long idMeeting;
    private String status;
    private LocalDateTime date;
    private String buyerAlias;
    private Long buyerId;
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
    private String buyerDistinctiveSign;
    private String sellerDistinctiveSign;
    private String sellerAdditionalInfo;
    private String buyerAdditionalInfo;

    public Long getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(Long idMeeting) {
        this.idMeeting = idMeeting;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getBuyerDistinctiveSign() {
        return buyerDistinctiveSign;
    }

    public void setBuyerDistinctiveSign(String buyerDistinctiveSign) {
        this.buyerDistinctiveSign = buyerDistinctiveSign;
    }

    public String getSellerDistinctiveSign() {
        return sellerDistinctiveSign;
    }

    public void setSellerDistinctiveSign(String sellerDistinctiveSign) {
        this.sellerDistinctiveSign = sellerDistinctiveSign;
    }

    public String getSellerAdditionalInfo() {
        return sellerAdditionalInfo;
    }

    public void setSellerAdditionalInfo(String sellerAdditionalInfo) {
        this.sellerAdditionalInfo = sellerAdditionalInfo;
    }

    public String getBuyerAdditionalInfo() {
        return buyerAdditionalInfo;
    }

    public void setBuyerAdditionalInfo(String buyerAdditionalInfo) {
        this.buyerAdditionalInfo = buyerAdditionalInfo;
    }
}
