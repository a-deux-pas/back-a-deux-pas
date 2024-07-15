package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) for representing user profile information.
 * <p>
 * This DTO encapsulates various attributes of a user's profile, such as alias,
 * biography, location details, profile picture URL, and inscription date.
 * </p>
 * <p>
 * It is typically used to transfer user profile data between different layers
 * of the application, such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class UserProfileResponseDTO {
    private Long id;
    private String alias;
    private String bio;
    private String city;
    private String postalCode;
    private String profilePicture;
    private String inscriptionDate;
    private String salesNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(String inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }
}
