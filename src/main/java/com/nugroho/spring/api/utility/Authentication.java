package com.nugroho.spring.api.utility;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class Authentication implements Serializable {

    private static final long serialVersionUID = -2550185165626007488L;

    public static class Bearer {

        public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

        public static String getUsernameFromBearerToken(String secret, String token) {
            return getClaimFromToken(secret, token, Claims::getSubject);
        }

        public static Date getExpirationDateFromToken(String secret, String token) {
            return getClaimFromToken(secret, token, Claims::getExpiration);
        }

        public static <T> T getClaimFromToken(String secret, String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(secret, token);
            return claimsResolver.apply(claims);
        }

        public static String generateToken(String secret, UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return doGenerateToken(secret, claims, userDetails.getUsername());
        }

        public static boolean validateToken(String secret, String token, UserDetails userDetails) {
            final String username = getUsernameFromBearerToken(secret, token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(secret, token));
        }

        private static Claims getAllClaimsFromToken(String secret, String token) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }

        private static boolean isTokenExpired(String secret, String token) {
            final Date expiration = getExpirationDateFromToken(secret, token);
            return expiration.before(new Date());
        }

        private static String doGenerateToken(String secret, Map<String, Object> claims, String subject) {
            return Jwts.builder().setClaims(claims).setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        }
    }

    public static class OAuth2 {

        public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

        public static String getUsernameFromBearerToken(String secret, String token) {
            return getClaimFromToken(secret, token, Claims::getSubject);
        }

        public static Date getExpirationDateFromToken(String secret, String token) {
            return getClaimFromToken(secret, token, Claims::getExpiration);
        }

        public static <T> T getClaimFromToken(String secret, String token, Function<Claims, T> claimsResolver) {
            final Claims claims = getAllClaimsFromToken(secret, token);
            return claimsResolver.apply(claims);
        }

        public static String generateToken(String secret, UserDetails userDetails) {
            Map<String, Object> claims = new HashMap<>();
            return doGenerateToken(secret, claims, userDetails.getUsername());
        }

        public static boolean validateToken(String secret, String token, UserDetails userDetails) {
            final String username = getUsernameFromBearerToken(secret, token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(secret, token));
        }

        private static Claims getAllClaimsFromToken(String secret, String token) {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }

        private static boolean isTokenExpired(String secret, String token) {
            final Date expiration = getExpirationDateFromToken(secret, token);
            return expiration.before(new Date());
        }

        private static String doGenerateToken(String secret, Map<String, Object> claims, String subject) {
            return Jwts.builder().setClaims(claims).setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        }
    }
}
