package adeuxpas.back.dto;

import java.util.List;

/**
 * Data Transfer Object (DTO) for representing information displayed on the
 * logged in home page.
 * <p>
 * This DTO encapsulates user's alias and information about a seller
 * {@link SellerHomeResponseDTO}
 * </p>
 * <p>
 * It is typically used to transfer data between different layers
 * of the application, such as between the controller and the service layer.
 * </p>
 * 
 * @author Léa Hadida
 */
public class LoggedInHomeResponseDTO {
    private String userAlias;
    private List<SellerHomeResponseDTO> seller;

    public LoggedInHomeResponseDTO(String userAlias, List<SellerHomeResponseDTO> seller) {
        this.userAlias = userAlias;
        this.seller = seller;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public List<SellerHomeResponseDTO> getSeller() {
        return seller;
    }

    public void setSeller(List<SellerHomeResponseDTO> seller) {
        this.seller = seller;
    }
}
