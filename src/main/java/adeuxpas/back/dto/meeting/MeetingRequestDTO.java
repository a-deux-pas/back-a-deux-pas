package adeuxpas.back.dto.meeting;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class MeetingRequestDTO {
    @NotNull
    public Long buyerId;
    @NotNull
    public Long sellerId;
    @NotNull
    public Long adId;
    @NotNull
    public Long proposedMeetingPlaceId;
    @NotNull
    public LocalDateTime date;
    private String buyerAdditionalInfo;
    private String buyerDistinctiveSign;

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

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getProposedMeetingPlaceId() {
        return proposedMeetingPlaceId;
    }

    public void setProposedMeetingPlaceId(Long proposedMeetingPlaceId) {
        this.proposedMeetingPlaceId = proposedMeetingPlaceId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getBuyerAdditionalInfo() {
        return buyerAdditionalInfo;
    }

    public void setBuyerAdditionalInfo(String buyerAdditionalInfo) {
        this.buyerAdditionalInfo = buyerAdditionalInfo;
    }

    public String getBuyerDistinctiveSign() {
        return buyerDistinctiveSign;
    }

    public void setBuyerDistinctiveSign(String buyerDistinctiveSign) {
        this.buyerDistinctiveSign = buyerDistinctiveSign;
    }

    @Override
    public String toString() {
        return "ProposedMeetingRequestDTO{" +
                "buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", adId=" + adId +
                ", proposedMeetingPlaceId=" + proposedMeetingPlaceId +
                ", date=" + date +
                ", buyerAdditionalInfo='" + buyerAdditionalInfo + '\'' +
                ", buyerDistinctiveSign='" + buyerDistinctiveSign + '\'' +
                '}';
    }
}
