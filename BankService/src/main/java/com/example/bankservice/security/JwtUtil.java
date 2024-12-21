package com.example.bankservice.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	 private static final String SECRET_KEY = "dGhpcyBpcyBhIHZlcnkgc3BlY2lhbCBzZWNyZXQga2V5IHRoYXQgd2lsbCBiZSB1c2VkIGZvciBKU1Qgc2lnbmVkIHB1cnBvc2Vz";
	    private SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
	 

    public String extractUsername(String token) {
        try {
            System.out.println("Validating token with secret key: " + secretKey); // Debugging
            String userNameGenerated =  Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return userNameGenerated;
        } catch (JwtException e) {
            System.out.println("JWT Parsing failed: " + e.getMessage());
            throw e; // Rethrow or handle accordingly
        }
    }

    public String extractRole(String token) {
        try {
            String role = Jwts.parserBuilder()
                              .setSigningKey(secretKey)
                              .build()
                              .parseClaimsJws(token)
                              .getBody()
                              .get("role", String.class);
            System.out.println("Extracted role: " + role);  // Debugging role extraction
            return role;
        } catch (JwtException e) {
            System.out.println("Error extracting role: " + e.getMessage());
            throw e;
        }
    }

    public boolean validateToken(String token, String username) {
        try {
            boolean isValid = (username.equals(extractUsername(token)) && !isTokenExpired(token));
            System.out.println("Is Token Valid: " + isValid);  // Debugging token validation
            return isValid;
        } catch (JwtException e) {
            System.out.println("Error validating token: " + e.getMessage());
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = extractExpiration(token);
        System.out.println("Token Expiration Date: " + expiration);  // Log expiration date
        return expiration.before(new Date());
    }

    private Date extractExpiration(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration();
        } catch (JwtException e) {
            System.out.println("Error extracting expiration date: " + e.getMessage());
            throw e;
        }
    }
}