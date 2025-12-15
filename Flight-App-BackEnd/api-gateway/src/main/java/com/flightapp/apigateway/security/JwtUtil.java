package com.flightapp.apigateway.security;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration:3600000}")
    private long jwtExpirationMs;

    private Key key() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration != null && expiration.before(new Date());
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token); // will throw if invalid
            return true;
        } catch (JwtException | IllegalArgumentException ex) {
            // you can add logger here if you want
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object rolesObj = claims.get("roles");   // same claim name as your friend

        if (rolesObj == null) {
            return Collections.emptyList();
        }

        // Case 1: roles already a collection
        if (rolesObj instanceof Collection<?> col) {
            if (CollectionUtils.isEmpty(col)) return Collections.emptyList();
            return col.stream().map(Object::toString).toList();
        }

        // Case 2: roles in string form like "[ROLE_USER, ROLE_ADMIN]"
        String s = rolesObj.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "");

        if (s.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.asList(s.split(","));
    }
}
