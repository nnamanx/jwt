package com.company.jwt.service.impl;

import com.company.jwt.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService implements IJwtService {

    @Value("${application.security.secret-key}")
    String secretKey;

    @Value("${application.security.access-token-expiration}")
    Long accessTokenExpiration;

    @Value("${application.security.refresh-token-expiration}")
    Long refreshTokenExpiration;

    @Override
    public Claims extractAllClaims(String jwt) {
        return Jwts
                .parser()
                .setSigningKey(getSiginKey())
                .parseClaimsJws(jwt)
                .getBody();

    }

    @Override
    public <T> T extractClaim(String jwt, Function<Claims, T> claimsResolved) {
        return claimsResolved.apply(extractAllClaims(jwt));
    }


    @Override
    public String extractUsername(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    @Override
    public Date extractExpiration(String jwt) {
        return extractClaim(jwt, Claims::getExpiration);
    }

    @Override
    public Key getSiginKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails, new HashMap<>());
    }

    @Override
    public String generateToken(UserDetails userDetails, Map<String, Object> extractClaims) {
        return buildToken(userDetails, extractClaims, accessTokenExpiration);
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails) {
        return buildToken(userDetails, new HashMap<>(), refreshTokenExpiration);
    }

    @Override
    public String buildToken(UserDetails userDetails, Map<String, Object> extractClaims, long expiration) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSiginKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean isTokenValid(String jwt, UserDetails userDetails) {
        return extractUsername(jwt).contains(userDetails.getUsername()) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return extractExpiration(jwt).before(new Date());
    }


    
}
