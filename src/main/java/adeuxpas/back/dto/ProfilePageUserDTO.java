package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) for representing user profile information on a profile page.
 * <p>
 * This DTO encapsulates various attributes of a user's profile, such as alias, biography, location details,
 * profile picture URL, and inscription date.
 * </p>
 * <p>
 * It is typically used to transfer user profile data between different layers of the application,
 * such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class ProfilePageUserDTO {
    private Long id;
    private String alias;
    private String bio;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String profilePicture;
    private String inscriptionDate;
    
    /**
     * Default constructor for ProfilePageUserDTO.
     */
    public ProfilePageUserDTO() {
    }

    /**
     * Constructor for ProfilePageUserDTO with all attributes.
     *
     * @param id              The unique identifier for the user.
     * @param alias           The alias or username of the user.
     * @param bio             The biography or description of the user.
     * @param country         The country of residence.
     * @param city            The city of residence.
     * @param street          The street address.
     * @param postalCode      The postal code or ZIP code.
     * @param profilePicture  The URL of the user's profile picture.
     * @param inscriptionDate The date of user's registration or inscription.
     */
    public ProfilePageUserDTO(Long id, String alias, String bio, String country, String city, String street,
            String postalCode, String profilePicture, String inscriptionDate) {
        this.id = id;
        this.alias = alias;
        this.bio = bio;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.profilePicture = profilePicture;
        this.inscriptionDate = inscriptionDate;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
}
