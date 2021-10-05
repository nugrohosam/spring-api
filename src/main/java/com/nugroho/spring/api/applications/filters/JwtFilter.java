package com.nugroho.spring.api.applications.filters;

import javax.servlet.http.HttpServletRequest;

import com.nugroho.spring.api.applications.requests.HeaderPayload;
import com.nugroho.spring.api.applications.requests.JwtPayload;
import com.nugroho.spring.api.utility.Authentication.Bearer;
import com.nugroho.spring.api.utility.Authentication.OAuth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter {

    @Autowired
    private JwtPayload jwtPayload;

    @Autowired
    private HeaderPayload headerPayload;

    private final String jwtBearer = "Bearer";
    private final String jwtOAuth2 = "OAuth2";

    @Value("${app.secret}")
    private String secret;

    public void doFilter(HttpServletRequest request) {
        String authorization = headerPayload.getAuthorization();
        if (authorization != null) {
            String username = null;
            if (authorization.contains(jwtBearer)) {
                var token = authorization.replace(jwtBearer + " ", "");
                username = Bearer.getUsernameFromBearerToken(secret, token);
            } else if (authorization.contains(jwtOAuth2)) {
                var token = authorization.replace(jwtOAuth2 + " ", "");
                username = OAuth2.getUsernameFromBearerToken(secret, token);
            }
            jwtPayload.setUsername(username);
        }
    }
}
