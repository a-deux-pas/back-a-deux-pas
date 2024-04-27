package adeuxpas.back.auth;

import adeuxpas.back.enums.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service class for handling JSON Web Tokens (JWT).
 <p>
 Contains methods to generate and retrieve token information.
 </p>
 <p>
 * This class is annotated with {@code @Service} to indicate that it is a service component
 * and should be automatically detected and registered as a Spring bean.
 </p>
 * @author Mircea Bardan
 */
@Service
public class JWTService {

    //@Value("${app.jwt.secret}")
    private final String jwtSecret = "8c3af27e3bae4f1f82a5c663841f0e33d8456a52416f2b5f5248b9406ebbea37";

    //@Value("${app.jwt.expiration}")
    private final int jwtExpiration = 43400000;

    /**
     * Generates a JWT token based on the provided user email and role.
     *
     * @param email The username for which the token is generated.
     * @param role     The role associated with the user.
     * @return The generated JWT token.
     */
    public String generateToken(String email, UserRole role) {
        return Jwts.builder()
                .subject(email)
                .claim("user", role.equals(UserRole.USER))
                .claim("admin",role.equals(UserRole.ADMIN))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    /**
     * Extracts the subject (sub) from the JWT token.
     *
     * @param token The JWT token from which to extract the subject.
     * @return The subject (sub) of the token.
     */
    public String getSub(String token){
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
    public Date getExpiration(String token){
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();
    }
}
