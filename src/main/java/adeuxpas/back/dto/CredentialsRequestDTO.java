package adeuxpas.back.dto;

import jakarta.validation.constraints.*;

/**
 * Data Transfer Object (DTO) representing a credentials request.
 * This class encapsulates the information required for a user signup and login
 * operation, including the user's email and password.
 * It provides getter and setter methods to access and modify all the fields.
 * <p>
 * Instances of this class are used to transfer credentials data between
 * different layers of the application, such as the controller and the service.
 * </p>
 *
 * @author Léa Hadida
 */
public class CredentialsRequestDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    // The string must contain:
    // (\d): at least one digit
    // ([a-z]): at least one one lowercase letter
    // ([A-Z]): at least one uppercase letter
    // ([!@#&()–[{}]:;',?/*~$^+=<>]): at least one special character from this set
    // {8,}: minimum length of 8 characters
    // (.*): anywhere within the string
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,}$")
    private String password;
    private boolean rememberMe;

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

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
