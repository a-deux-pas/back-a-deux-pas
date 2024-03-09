package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) representing a signup request.
 * This class encapsulates the information required for a user signup operation,
 * including the user's email, password, alias, bio etc.
 * It provides getter and setter methods to access and modify all the fields.
 * <p>
 * Instances of this class are used to transfer signup request data between different layers of the application,
 * such as the presentation layer (e.g., REST controller) and the service layer.
 * </p>
 *
 * @author Mircea Bardan
 */
public class SignupRequest {
    private String email;
    private String password;
    private String alias;
    private String bio;
    private String country;
    private String city;
    private String street;
    private String postalCode;
    private String profilePicture;

    /**
     * Constructor for {@code SignupRequest}.
     * @param email The {@code SignupRequest} sender's email.
     * @param password The {@code SignupRequest} sender's password.
     * @param alias The {@code SignupRequest} sender's alias.
     * @param bio The {@code SignupRequest} sender's bio.
     * @param country The {@code SignupRequest} sender's country.
     * @param city The {@code SignupRequest} sender's city.
     * @param street The {@code SignupRequest} sender's street.
     * @param postalCode The {@code SignupRequest} sender's postal code.
     * @param profilePicture The {@code SignupRequest} sender's profile picture.
     */
    public SignupRequest(String email, String password, String alias,
                         String bio, String country, String city, String street,
                         String postalCode, String profilePicture) {
        this.email = email;
        this.password = password;
        this.alias = alias;
        this.bio = bio;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
