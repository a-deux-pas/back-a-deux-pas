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
                // Configure the default Cross-Origin Resource Sharing (CORS) policy for the
                // HTTP security.
                // Without the '.cors(cors -> cors.configure(http))' line, the default CORS configuration provided by Spring Security
                // would still be in effect,
                // permitting same-origin resource sharing and denying cross-origin requests:
                // .cors(cors -> cors.configure(http))
                // The explicit inclusion of this line simply ensures that the default
                // configuration is applied explicitly
                // and provides a hook for further customization, needed in the near future:
                // FOR EXAMPLE, we'll need to customize the above default CORS configuration,
                // when we start making requests to our Front End,
                // to only allow cross-origin requests from our Angular web server, running at
                // localhost:4200, like so:
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("https://localhost:4200")); // self-explanatory
                    configuration.setAllowedMethods(Arrays.asList("GET", "PATCH", "POST", "PUT", "DELETE")); // self-explanatory
                    // For ex: standard headers like Content-Type, Authorization, etc.,
                    // but also custom headers that the frontend application might include in its
                    // requests.
                    // In a production environment it's more secure to explicitly specify the
                    // headers that are allowed, rather than allowing them all with the * symbol.
                    configuration.setAllowedHeaders(List.of("Content-Type", "Authorization"));
                    return configuration;
                }))
                // Disable Cross-Site Request Forgery (CSRF) protection for our app,
                // since it uses stateless API with token-based authentication (JWT),
                // and CSRF protection is less relevant in this scenario
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        // We explicitly define all the endpoints that we permit open access to.
                        // This practice complies with the 'Default Deny'/'Implicit Deny' principle, that is a fundamental aspect
                                // of security practices and advises that, by default,
                                // all access should be denied unless explicitly allowed.
                        // Open access routes (to be verified/updated) :
                        .requestMatchers(
                                "/api/account/create",
                                "/api/auth/**",
                                "/api/ads/{id}",
                                "/api/ads/adTablist/{userId}",
                                "/api/ads/favorites/{userId}",
                                "/api/ads/{adId}/{loggedInUserId}",
                                "/api/ads/adPageContentList/{publisherId}/{loggedInUserId}/{adId}",
                                "/api/ads/similarAdsList/{category}/{publisherId}/{userId}",
                                "/api/ads/list",
                                "/api/users/cities-and-postal-codes",
                                "/api/users/{id}/alias-and-location",
                                "/api/users/{userAlias}",
                                "/api/users/{userAlias}/presentation",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
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
