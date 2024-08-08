package adeuxpas.back.dto.meeting;

import adeuxpas.back.enums.MeetingStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MeetingResponseDTO {

    private Long idMeeting;
    private MeetingStatus status;
    private LocalDateTime date;
    private Long buyerId;
    private Long sellerId;
    private String buyerAlias;
    private String sellerAlias;
    private LocalDateTime buyerInscriptionDate;
    private LocalDateTime sellerInscriptionDate;
    private String buyerCity;
    private String sellerCity;
    private String buyerAdditionalInfo;
    private String sellerAdditionalInfo;
    private String buyerDistinctiveSign;
    private String sellerDistinctiveSign;
    private String buyerProfilePictureUrl;
    private String sellerProfilePictureUrl;
    private String meetingPlaceName;
    private String postalCode;
    private String city;
    private String street;
    private Long adId;
    private String adPublisherAlias;
    private String adTitle;
    private BigDecimal adPrice;
    private String adPictureUrl;
    private Boolean isValidatedByBuyer;
    private Boolean isValidatedBySeller;

    // Getters and setters

    public Long getIdMeeting() {
        return idMeeting;
    }

    public void setIdMeeting(Long idMeeting) {
        this.idMeeting = idMeeting;
    }

    public MeetingStatus getStatus() {
        return status;
    }

    public void setStatus(MeetingStatus status) {
        this.status = status;
    }

    public String getBuyerAdditionalInfo() {
        return buyerAdditionalInfo;
    }

    public void setBuyerAdditionalInfo(String buyerAdditionalInfo) {
        this.buyerAdditionalInfo = buyerAdditionalInfo;
    }

    public String getSellerAdditionalInfo() {
        return sellerAdditionalInfo;
    }

    public void setSellerAdditionalInfo(String sellerAdditionalInfo) {
        this.sellerAdditionalInfo = sellerAdditionalInfo;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public LocalDateTime getBuyerInscriptionDate() {
        return buyerInscriptionDate;
    }

    public void setBuyerInscriptionDate(LocalDateTime buyerInscriptionDate) {
        this.buyerInscriptionDate = buyerInscriptionDate;
    }

    public LocalDateTime getSellerInscriptionDate() {
        return sellerInscriptionDate;
    }

    public void setSellerInscriptionDate(LocalDateTime sellerInscriptionDate) {
        this.sellerInscriptionDate = sellerInscriptionDate;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
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

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public String getAdPublisherAlias() {
        return adPublisherAlias;
    }

    public void setAdPublisherAlias(String adPublisherAlias) {
        this.adPublisherAlias = adPublisherAlias;
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

    public Boolean getValidatedByBuyer() {
        return isValidatedByBuyer;
    }

    public void setValidatedByBuyer(Boolean validatedByBuyer) {
        isValidatedByBuyer = validatedByBuyer;
    }

    public Boolean getValidatedBySeller() {
        return isValidatedBySeller;
    }

    public void setValidatedBySeller(Boolean validatedBySeller) {
        isValidatedBySeller = validatedBySeller;
    }

}
