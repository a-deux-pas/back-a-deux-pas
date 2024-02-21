package adeuxpas.back.config;

import adeuxpas.back.auth.JWTFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Configuration class for Spring Security setup.
 * <p>
 * Configures security filters and permissions for the application.
 * </p>
 * @author Mircea Bardan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    /**
     * Constructor for SecurityConfig.
     * @param jwtFilter The JWT filter for authentication.
     */
    public SecurityConfig(@Autowired JWTFilter jwtFilter){
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configures the security filter chain.
     * @param http The HttpSecurity object for configuring security.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configure(http)) // configure Cross-Origin Resource Sharing (CORS) for the HTTP security.
                .csrf(CsrfConfigurer::disable) // disable Cross-Site Request Forgery (CSRF) protection during development.
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/signup", "/api/login").permitAll()
                        .requestMatchers("/api/content").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers("/api/admin-page").hasAuthority("ADMIN"))
                .sessionManagement(session -> session
                        // Set the session creation policy to STATELESS,
                        // meaning no session will be created or used for authentication.
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) );

        // Add our custom JWTFilter before the UsernamePasswordAuthenticationFilter in the filter chain.
        // It ensures that the JWT authentication filter is applied before the username-password authentication filter.
        http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
