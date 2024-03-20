package adeuxpas.back.entity;

import adeuxpas.back.enums.AccountStatus;
import adeuxpas.back.enums.UserRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Entity class representing a user in the application.
 * This class encapsulates user-related information, such as email, password, profile details, account status etc.
 * Instances of this class are persisted in the database and typically interact with the UserRepository.
 *
 * @author Mircea Bardan
 */
@Entity
public class User {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 150) 
    private String email;
    private String password;
    @Column(length = 150) 
    private String alias;
    private String bio;
    @Column(length = 150) 
    private String country;
    @Column(length = 150) 
    private String city;
    @Column(length = 200) 
    private String street;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "inscription_date")
    private LocalDate inscriptionDate;
    @Column(name = "account_status")
    private AccountStatus accountStatus;
    private UserRole role;


    // no args constructor
    public User() {}

    /**
     * All args constructor for {@code User}.
     * @param email The {@code User}'s email.
     * @param password The {@code User}'s password.
     * @param alias The {@code User}'s alias.
     * @param bio The {@code User}'s bio.
     * @param country The {@code User}'s country.
     * @param city The {@code User}'s city.
     * @param street The {@code User}'s street.
     * @param postalCode The {@code User}'s postal code.
     * @param profilePicture The {@code User}'s profile picture.
     * @param inscriptionDate The {@code User}'s inscription date.
     * @param accountStatus The {@code User}'s account status.
     * @param role The {@code User}'s role.
     */
    // all args constructor
    public User(String email, String password, String alias, String bio, String country, String city,
                String street, String postalCode, String profilePicture, LocalDate inscriptionDate,
                AccountStatus accountStatus, UserRole role) {
        this.email = email;
        this.password = password;
        this.alias = alias;
        this.bio = bio;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
        this.profilePicture = profilePicture;
        this.inscriptionDate = inscriptionDate;
        this.accountStatus = accountStatus;
        this.role = role;
    }

    // getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public LocalDate getInscriptionDate() {
        return inscriptionDate;
    }

    public void setInscriptionDate(LocalDate inscriptionDate) {
        this.inscriptionDate = inscriptionDate;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    // toString
    @Override
    public String toString(){
        return this.email;
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(email, user.email);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
