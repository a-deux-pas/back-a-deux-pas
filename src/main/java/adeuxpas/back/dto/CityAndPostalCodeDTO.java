package adeuxpas.back.dto;

import java.util.Objects;

public class CityAndPostalCodeDTO {
    String city;
    String postalCode;

    public CityAndPostalCodeDTO(String city, String postalCode) {
        this.city = city;
        this.postalCode = postalCode;
    }

    public CityAndPostalCodeDTO() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityAndPostalCodeDTO that)) return false;
        return Objects.equals(city, that.city) && Objects.equals(postalCode, that.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, postalCode);
    }
}
