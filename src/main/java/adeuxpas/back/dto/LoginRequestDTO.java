package adeuxpas.back.dto;

/**
 * Data Transfer Object (DTO) representing a login request.
 * This class encapsulates the information required for a user login operation,
 * including the user's email and password.
 * It provides getter and setter methods to access and modify the email and password fields.
 * <p>
 * Instances of this class are used to transfer login request data between different layers of the application,
 * such as the presentation layer (e.g., REST controller) and the service layer.
 * </p>
 *
 * @author Mircea Bardan
 */
public class LoginRequestDTO {
    private String email;
    private String password;

    /**
     * Constructor for {@code LoginRequestDTO}.
     * @param email The {@code LoginRequestDTO} sender's email.
     * @param password The {@code LoginRequestDTO} sender's password.
     */
    public LoginRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // no args constructor
    public LoginRequestDTO(){}

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
