package com.company.jwt.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface IJwtService {

    Claims extractAllClaims(String jwt);
    <T> T extractClaim(String jwt, Function<Claims, T> claimsResolved);
    String extractUsername(String jwt);
    Date extractExpiration(String jwt);
    Key getSiginKey();
    String generateToken(UserDetails userDetails);
    String generateToken(UserDetails userDetails, Map<String, Object> extractClaims);
    String generateRefreshToken(UserDetails userDetails);
    String buildToken(UserDetails userDetails, Map<String, Object> extractClaims, long expiration);
    boolean isTokenValid(String jwt, UserDetails userDetails);
}
