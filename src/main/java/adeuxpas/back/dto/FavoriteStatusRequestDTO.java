package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) for representing a favorite ad status.
 * <p>
 * This DTO encapsulates the favorite status of an ad.
 * </p>
 * <p>
 * It is typically used to transfer data between different layers of the
 * application, such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class FavoriteStatusRequestDTO {
    private boolean isFavorite;

    // Getters and Setters
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }
}
