package adeuxpas.back.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

import java.io.IOException;
import java.util.Date;

/**
 * Custom filter that intercepts incoming requests, checks for the presence of
 * an Authorization header,
 * and verifies the validity of the JWT token before passing the request to the
 * next filter or Servlet.
 * <p>
 * This filter runs once for each incoming request when added to the
 * FilterChain.
 * </p>
 * <p>
 * It extracts the JWT token from the Authorization header, validates its
 * authenticity and expiration,
 * and sets the authenticated user's details in the Spring Security context for
 * further processing.
 * </p>
 * <p>
 * This class is annotated with {@code @Component} to indicate that it is a
 * component
 * and should be automatically detected and registered as a Spring bean.
 * </p>
 *
 * @author Mircea Bardan
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    /**
     * Constructs a new {@code JWTFilter} with the specified JWT service and user
     * details service.
     *
     * @param jwtService         The service for JWT token handling.
     * @param userDetailsService The service for loading user details.
     */
    public JWTFilter(@Autowired JWTService jwtService,
            @Autowired UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Performs the actual filter processing for each incoming request.
     *
     * @param request     The HTTP servlet request.
     * @param response    The HTTP servlet response.
     * @param filterChain The filter chain for subsequent filters.
     * @throws ServletException If an error occurs during servlet processing.
     * @throws IOException      If an I/O error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Check the presence of the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            // Extract the token from the Authorization header, removing the 'Bearer '
            // prefix
            String token = authorizationHeader.substring(7);

            try {
                // Extract the sub (email) from the token, load its user details and pass them
                // to a Spring Security UserDetails object
                UserDetails user = this.userDetailsService.loadUserByUsername(this.jwtService.getSub(token));

                // Check JWT expiration
                if (this.jwtService.getExpiration(token).after(new Date())) {
                    // The UserDetails object is used to pass along our User's details to another
                    // Spring Security class:
                    // This UsernamePasswordAuthToken will finally be the object used by Spring in
                    // its Security Context
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            user.getAuthorities());

                    // Set the UsernamePasswordAuthToken as the authentication token for the current
                    // security context.
                    // This ensures that the user is authenticated for the current request.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            } catch (ExpiredJwtException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token is expired");
                return; // Prevent further filter processing
            } catch (JwtException | IllegalArgumentException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return; // Prevent further filter processing
            }
        }

        // Hand over control to the next filter in the chain, or to the Servlet if there
        // are no more filters.
        // In our case, the next filter call would be to the
        // UsernamePasswordAuthenticationFilter (cf. SecurityConfig class)
        filterChain.doFilter(request, response);
    }
}
