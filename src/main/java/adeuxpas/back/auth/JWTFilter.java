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

import java.io.IOException;
import java.util.Date;

/**
 * Custom filter that checks the Request sent by the client, looking for an Authorization Header
 * and verifying the presence and validity of the JWT within, before authenticating it.

 * Runs once for each Request when added to the FilterChain.

 * @author Mircea Bardan
 */
@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    public JWTFilter(@Autowired JWTService jwtService,
                     @Autowired UserDetailsService userDetailsService){
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
        if (authorizationHeader != null){
            // Extract the token from the Authorization header
            // removing the word 'Bearer' from the beginning of the token string (begin index=7)
            String token = authorizationHeader.substring(7);
            // Extract the Sub (email) from the token, pass it to the UserDetailsService object,
            // which, internally, uses it to load our domain User from the DB, and then
            // pass our User's authentication details (email, password and roles) to a Spring Security UserDetails object
            UserDetails user = this.userDetailsService.loadUserByUsername(this.jwtService.getSub(token));

            // Check JWT expiration
            if (this.jwtService.getExpiration(token).after(new Date())){
                // The previously created UserDetails object is used to pass along our User's details to another Spring Security class:
                // This UsernamePasswordAuthToken will be the object used by Spring in its Security Context
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword(),
                        user.getAuthorities()
                );

                // Set the previously created UsernamePasswordAuthToken as the authentication token for the current security context.
                // This ensures that the user is authenticated for the current request.
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        // Hand over control to the next filter in the chain, or to the Servlet if there are no more filters
        // In our case, the next filter called would be the UsernamePasswordAuthenticationFilter (cf. SecurityConfig class)
        filterChain.doFilter(request, response);
    }
}
