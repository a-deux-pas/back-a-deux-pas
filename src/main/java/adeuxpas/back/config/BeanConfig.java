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
 * @author : Mircea Bardan
 */
@Configuration
public class BeanConfig {

    /**
     * Bean definition for BCryptPasswordEncoder.
     * <p>
     * This method registers an instance of BCryptPasswordEncoder
     * as a Spring Bean. It can be used for encoding and decoding passwords
     * securely.
     * </p>
     *
     * @return An instance of BCryptPasswordEncoder.
     */
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }
}
