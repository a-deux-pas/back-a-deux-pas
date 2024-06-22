package adeuxpas.back.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/**
 * Entity representing a user's preferred meeting place.
 * Contains information about preferred meeting places, address and name.
 * 
 * Persisted in the database and interacts with PreferredMeetingPlaceRepository.
 *
 * @author Léa Hadida
 */
@Entity
public class PreferredMeetingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String street;

    @Column(length = 150, nullable = false)
    private String city;

    @Column(name = "postal_code", length = 5, nullable = false)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Default constructor for PreferredMeetingPlace.
     */
    public PreferredMeetingPlace() {
    }

    /**
     * Constructor for PreferredMeetingPlace with all attributes except ID.
     *
     * @param name       The name of the preferred meeting place.
     * @param street     The street address of the preferred meeting place.
     * @param city       The city of the preferred meeting place.
     * @param postalCode The postal code of the preferred meeting place.
     */
    public PreferredMeetingPlace(String name, String street, String city, String postalCode, User user) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
