package com.sabancihan.amadeus_flight.security.jwt;

import com.sabancihan.amadeus_flight.security.config.SecretConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtils {
    private final SecretConfig secretConfig;


    private SecretKey key() {
        return Keys.hmacShaKeyFor(secretConfig.getSecretKey().getBytes());
    }

    public String generateToken(Authentication authentication) {
        Date now = new Date();


        return Jwts.builder()
                .subject(authentication.getName())
                .issuedAt(now)
                .claim("roles", authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .expiration(new Date(now.getTime() + secretConfig.getTokenExpirationTime()))
                .signWith(key())
                .compact();
    }


public Claims getClaimsFromToken(String authToken) {
    return Jwts.parser()
            .verifyWith(key())
            .build()
            .parseSignedClaims(authToken).getPayload();
}


    public boolean validateToken(String authToken) {
        try {
            log.info("Parsing jwt token");
             Jwts.parser()
                     .verifyWith(key())
                        .build()
                        .parse(authToken);


            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }




}
