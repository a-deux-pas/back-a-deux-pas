package adeuxpas.back.dto;

public class PreferredMeetingPlaceDTO {
    private Long id;
    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String name;
    private Long userId;
    
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
