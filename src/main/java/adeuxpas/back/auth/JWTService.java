package adeuxpas.back.auth;

import adeuxpas.back.enums.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service class for handling JSON Web Tokens (JWT).
 * <p>
 * Contains methods to generate and retrieve token information.
 * </p>
 * <p>
 * This class is annotated with {@code @Service} to indicate that it is a
 * service component
 * and should be automatically detected and registered as a Spring bean.
 * </p>
 * 
 * @author Mircea Bardan
 */
@Service
public class JWTService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    @Value("${app.jwt.expiration.remember}")
    private long jwtExpirationRemember;

    /**
     * Generates a JWT token based on the provided user id and role.
     *
     * @param id   The user id for which the token is generated.
     * @param role The role associated with the user.
     * @return The generated JWT token.
     */
    public String generateToken(Long id, UserRole role, boolean rememberMe) {
        Date now = new Date();
        long expirationTime = rememberMe ? jwtExpirationRemember : jwtExpiration;

        return Jwts.builder()
                .subject(Long.toString(id))
                .claim("user", role.equals(UserRole.USER))
                .claim("admin", role.equals(UserRole.ADMIN))
                .issuedAt(new Date())
                .expiration(new Date(now.getTime() + expirationTime))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    /**
     * Extracts the subject (sub) from the JWT token.
     *
     * @param token The JWT token from which to extract the subject.
     * @return The subject (sub) of the token.
     */
    public String getSub(String token) {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    /**
     * Retrieves the expiration date of the JWT token.
     *
     * @param token The JWT token from which to extract the expiration date.
     * @return The expiration date of the token.
     */
    public Date getExpiration(String token) {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
