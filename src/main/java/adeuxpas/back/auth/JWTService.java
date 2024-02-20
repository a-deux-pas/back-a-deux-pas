package adeuxpas.back.auth;

import adeuxpas.back.enums.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

// Service class for the JWT
// Contains methods to generate, get the validity and the subject of the JWT
@Service
public class JWTService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpiration;

    public String generateToken(String username, UserRole role) {
        return Jwts.builder()
                .subject(username)
                .claim("user", role.equals(UserRole.USER))
                .claim("admin",role.equals(UserRole.ADMIN))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public String getSub(String token){
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

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
