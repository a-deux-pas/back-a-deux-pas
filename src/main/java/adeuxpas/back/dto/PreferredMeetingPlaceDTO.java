package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) for representing preferred meeting place.
 * <p>
 * This DTO encapsulates information about a user's preferred meeting place,
 * including country, postal code, city, street, name, and user ID.
 * </p>
 * <p>
 * It is typically used to transfer preferred meeting place data between different layers of the application,
 * such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class PreferredMeetingPlaceDTO {
    private Long id;
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String name;
    private Long userId;

    /**
     * Default constructor for PreferredMeetingPlaceDTO.
     */
    public PreferredMeetingPlaceDTO() {
    }

    /**
     * Constructor for PreferredMeetingPlaceDTO with all attributes.
     *
     * @param id          The unique identifier for the preferred meeting place.
     * @param country     The country of the preferred meeting place.
     * @param postalCode  The postal code or ZIP code of the preferred meeting place.
     * @param city        The city of the preferred meeting place.
     * @param street      The street address of the preferred meeting place.
     * @param name        The name or description of the preferred meeting place.
     * @param userId      The ID of the user associated with the preferred meeting place.
     */
    public PreferredMeetingPlaceDTO(Long id, String country, String postalCode, String city, String street, String name,
            Long userId) {
        this.id = id;
        this.country = country;
        this.postalCode = postalCode;
        this.city = city;
        this.street = street;
        this.name = name;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
