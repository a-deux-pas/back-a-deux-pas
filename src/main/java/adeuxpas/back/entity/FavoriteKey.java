package adeuxpas.back.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * Embeddable class representing the composite key for favorite.
 * Contains the user's id and the ad's id.
 * Used as embedded key in the {@link Favorite} entity to map the many-to-many
 * relationship between users and ads.
 * 
 * @author Léa Hadida
 */

@Embeddable
public class FavoriteKey implements Serializable {

    private Long userId;

    private Long adId;

    public FavoriteKey() {
    }

    public FavoriteKey(Long userId, Long adId) {
        this.userId = userId;
        this.adId = adId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        FavoriteKey that = (FavoriteKey) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(adId, that.adId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, adId);
    }
}
