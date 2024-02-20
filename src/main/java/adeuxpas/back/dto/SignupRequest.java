package adeuxpas.back.dto;

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
