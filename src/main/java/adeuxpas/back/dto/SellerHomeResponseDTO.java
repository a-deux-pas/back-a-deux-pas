package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) for representing information about a seller.
 * 
 * <p>
 * This DTO encapsulates information about a seller, such as their alias,
 * city, and sales number.
 * </p>
 * <p>
 * It is typically used to transfer data between different layers
 * of the application, such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class SellerHomeResponseDTO {
    private String id;
    private String alias;
    private String city;
    private String salesNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSalesNumber() {
        return salesNumber;
    }

    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }
}
