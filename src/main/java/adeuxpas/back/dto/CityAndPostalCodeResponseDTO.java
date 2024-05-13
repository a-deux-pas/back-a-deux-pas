package adeuxpas.back.dto;

import java.util.Objects;

/**
 * Data Transfer Object (DTO) representing a unique postal code and its city.
 * <p>
 * This DTO class contains fields for representing a unique postal code and its associated city.
 * It is used for populating the city filter component of the front end client application,
 * in order to let the user filter the ads by any postal code that exists in the Database.
 * </p>
 *
 * @author Mircea Bardan
 */
public class CityAndPostalCodeResponseDTO {
    String city;
    String postalCode;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityAndPostalCodeResponseDTO that)) return false;
        return Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postalCode);
    }
}
