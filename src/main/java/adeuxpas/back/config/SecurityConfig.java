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
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for Spring Security setup.
 * <p>
 * Configures security filters and permissions for the application.
 * </p>
 *
 * @author Mircea Bardan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JWTFilter jwtFilter;

    /**
     * Constructor for SecurityConfig.
     * 
     * @param jwtFilter The JWT filter for authentication.
     */
    public SecurityConfig(@Autowired JWTFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configures the security filter chain.
     * 
     * @param http The HttpSecurity object for configuring security.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Configure the Cross-Origin Resource Sharing (CORS) policy for the
                // HTTP security.
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    //configuration.setAllowedOriginPatterns(List.of("http://localhost:*"));
                    configuration.setAllowedOrigins(List.of("http://localhost", "http://localhost:4200", "http://localhost:80", "https://front-a-deux-pas-f9087962c016.herokuapp.com")); // self-explanatory
                    configuration.setAllowedMethods(Arrays.asList("GET", "PATCH", "POST", "PUT", "DELETE")); // self-explanatory
                    // For ex: standard headers like Content-Type, Authorization, etc.,
                    // but also custom headers that the frontend application might include in its
                    // requests.
                    configuration.setAllowedHeaders(List.of("Content-Type", "Authorization"));
                    return configuration;
                }))
                // Disable Cross-Site Request Forgery (CSRF) protection for our app,
                // since it uses stateless API with token-based authentication (JWT),
                // and CSRF protection is less relevant in this scenario
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        // We explicitly define all the endpoints that we permit open access to.
                        // (some of them might seem that they shouldn't belong in this list, but they are needed, as per our business logic)
                        // This practice complies with the 'Default Deny'/'Implicit Deny' principle, that is a fundamental aspect
                                // of security practices and advises that, by default,
                                // all access should be denied unless explicitly allowed.
                        // Open access routes (to be verified/updated) :
                        .requestMatchers(
                                "/api/auth/**",
                                "/api/ads/{adId}/{loggedInUserId}",
                                "/api/ads/adPageContentList/{publisherId}/{loggedInUserId}/{adId}",
                                "/api/ads/similarAdsList/{category}/{publisherId}/{userId}",
                                "/api/ads/list",
                                "/api/users/cities-and-postal-codes",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/api/webhooks/**"
                        ).permitAll()
                        // Any other request must be authenticated
                        .anyRequest().authenticated()
                        )
                .sessionManagement(session -> session
                        // Set the session creation policy to STATELESS,
                        // meaning no session will be created or used for authentication.
                        // This aligns with the stateless nature of JWT authentication
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add our custom JWTFilter before the UsernamePasswordAuthenticationFilter in
        // the filter chain.
        // It ensures that the JWT authentication filter is applied before the
        // username-password authentication filter.
        http.addFilterBefore(this.jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
