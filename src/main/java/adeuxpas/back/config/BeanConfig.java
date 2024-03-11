package adeuxpas.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class that registers external dependencies as Beans
 * into the Spring Application Context, making them available for injection
 * and other Spring features.
 * <p>
 * This class provides a central location for defining and configuring
 * Beans that are required by the application.
 * </p>
 * @author Mircea Bardan
 */
@Configuration
public class BeanConfig {
    /**
     * Returns an instance of the {@code BCryptPasswordEncoder} used to hash passwords.
     * @return The {@code BCryptPasswordEncoder} instance.
     */
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
