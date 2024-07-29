package adeuxpas.back.dto.user;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) for representing a user alias and their location.
 * <p>
 * This DTO encapsulates information about a user, including alias, city and
 * postal code.
 * </p>
 * <p>
 * It is typically used to transfer data between different layers of the
 * application, such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class UserAliasAndLocationResponseDTO {
    private String alias;
    private String city;
    private String postalCode;
    private Boolean isExistingLocationWithAds;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Boolean getIsExistingLocationWithAds() {
        return isExistingLocationWithAds;
    }

    public void setIsExistingLocationWithAds(Boolean isExistingLocationWithAds) {
        this.isExistingLocationWithAds = isExistingLocationWithAds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserAliasAndLocationResponseDTO that))
            return false;
        return Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postalCode);
    }
}
