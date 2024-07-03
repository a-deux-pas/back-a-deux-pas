package adeuxpas.back.dto;

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
}
