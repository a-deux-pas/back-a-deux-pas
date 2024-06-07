package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) representing a credentials request.
 * This class encapsulates the information required for a user signup operation,
 * including the user's email and password.
 * It provides getter and setter methods to access and modify all the fields.
 * <p>
 * Instances of this class are used to transfer credentials data between
 * different layers of the application,
 * such as the presentation layer (e.g., REST controller) and the service layer.
 * </p>
 *
 * @author Léa Hadida
 */
public class CredentialsRequestDTO {
    private String email;
    private String password;

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
}
