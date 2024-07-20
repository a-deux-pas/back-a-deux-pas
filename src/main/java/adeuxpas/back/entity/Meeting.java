package adeuxpas.back.entity;

import adeuxpas.back.enums.MeetingStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity class representing a meeting.
 * This class encapsulates meeting-related information, such as status, date, additional info, etc.
 * Instances of this class are persisted to the database by the MeetingRepository.
 */
@Entity
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMeeting;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeetingStatus status;

    @Column(name = "buyer_additional_info", length = 255)
    private String buyerAdditionalInfo;

    @Column(name = "seller_additional_info", length = 255)
    private String sellerAdditionalInfo;

    @Column(name = "buyer_distinctive_sign", length = 150)
    private String buyerDistinctiveSign;

    @Column(name = "seller_distinctive_sign", length = 150)
    private String sellerDistinctiveSign;

    @Column(nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne
    @JoinColumn(name = "meeting_place_id", nullable = false)
    private PreferredMeetingPlace meetingPlace;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private PreferredSchedule schedule;

    @ManyToMany
    @JoinTable(
            name = "ads_meetings",
            joinColumns = @JoinColumn(name = "meeting_id"),
            inverseJoinColumns = @JoinColumn(name = "ad_id")
    )
    private Set<Ad> ads;

    @Column(name = "buyer_inscription_date")
    private LocalDateTime buyerInscriptionDate;

    @Column(name = "seller_inscription_date")
    private LocalDateTime sellerInscriptionDate;


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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public PreferredMeetingPlace getMeetingPlace() {
        return meetingPlace;
    }

    public void setMeetingPlace(PreferredMeetingPlace meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public PreferredSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(PreferredSchedule schedule) {
        this.schedule = schedule;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "idMeeting=" + idMeeting +
                ", status='" + status + '\'' +
                ", buyerAdditionalInfo='" + buyerAdditionalInfo + '\'' +
                ", sellerAdditionalInfo='" + sellerAdditionalInfo + '\'' +
                ", buyerDistinctiveSign='" + buyerDistinctiveSign + '\'' +
                ", sellerDistinctiveSign='" + sellerDistinctiveSign + '\'' +
                ", date=" + date +
                ", buyer=" + (buyer != null ? buyer.getId() : "null") +
                ", seller=" + (seller != null ? seller.getId() : "null") +
                ", meetingPlace=" + (meetingPlace != null ? meetingPlace.getId() : "null") +
                ", schedule=" + (schedule != null ? schedule.getId() : "null") +
                ", ads=" + (ads != null ? ads.size() : 0) +
                ", buyerInscriptionDate=" + buyerInscriptionDate +
                ", sellerInscriptionDate=" + sellerInscriptionDate +
                '}';
    }
}
