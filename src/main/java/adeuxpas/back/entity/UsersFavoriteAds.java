package adeuxpas.back.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

/**
 * Entity representing a user's favorite ad.
 * Contains the user's id, the ad's id and the date when the ad was added as
 * favorite.
 * 
 * Persisted in the database and interacts with FavoriteRepository.
 *
 * @author Léa Hadida
 */
@Entity
@Table(name = "users_favorite_ads")
public class UsersFavoriteAds {

    // combination of the user's id and the ad's id
    @EmbeddedId
    private UsersFavoriteAdsKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("adId")
    @JoinColumn(name = "ad_id", nullable = false)
    private Ad ad;

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;

    public UsersFavoriteAds() {
    }

    public UsersFavoriteAds(UsersFavoriteAdsKey id, User user, Ad ad, LocalDateTime addedAt) {
        this.id = id;
        this.user = user;
        this.ad = ad;
        this.addedAt = addedAt;
    }

    public UsersFavoriteAdsKey getId() {
        return id;
    }

    public void setId(UsersFavoriteAdsKey id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }
}
