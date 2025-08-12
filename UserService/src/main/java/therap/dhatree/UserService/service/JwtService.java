package therap.dhatree.UserService.service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    // For demo. In prod, inject via env and rotate keys.
    private static final String BASE64_SECRET = System.getenv().getOrDefault("JWT_SECRET_BASE64",
            "c3VwZXItc2VjcmV0LXN1cGVyLXNlY3JldC1zdXBlci1zZWNyZXQ=");

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(BASE64_SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(String sub, String email, String userType, String provider, long ttlSeconds) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(sub)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(ttlSeconds)))
                .addClaims(Map.of(
                        "email", email,
                        "userType", userType,
                        "provider", provider
                ))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            extractAllClaims(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
