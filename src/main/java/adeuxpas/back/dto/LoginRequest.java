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
public class LoginRequest {
    private String email;
    private String password;

    /**
     * Constructor for {@code LoginRequest}.
     * @param email The {@code LoginRequest} sender's email.
     * @param password The {@code LoginRequest} sender's password.
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
}
