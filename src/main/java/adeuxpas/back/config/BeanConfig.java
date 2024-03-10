package adeuxpas.back.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${cloud.name}")
    private String cloudName;
    @Value("${cloud.key}")
    private String apiKey;
    @Value("${cloud.secret}")
    private String apiSecret;

    /**
     * Returns an instance of the {@code BCryptPasswordEncoder} used to hash passwords.
     * @return The {@code BCryptPasswordEncoder} instance.
     */
    @Bean
    public BCryptPasswordEncoder getEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary getCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", cloudName,
            "api_key", apiKey,
            "api_secret", apiSecret));
    }
}
