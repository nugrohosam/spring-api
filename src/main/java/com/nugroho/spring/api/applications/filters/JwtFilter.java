package com.nugroho.spring.api.applications.filters;

import javax.servlet.http.HttpServletRequest;

import com.nugroho.spring.api.applications.requests.HeaderPayload;
import com.nugroho.spring.api.applications.requests.JwtPayload;
import com.nugroho.spring.api.utility.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter {

    @Autowired
    private JwtPayload jwtPayload;

    @Autowired
    private HeaderPayload headerPayload;

    private final String jwtBearer = "Bearer";
    private final String jwtOAuth2 = "OAuth2";

    public void doFilter(HttpServletRequest request) {
        String authorization = headerPayload.getAuthorization();
        if (authorization != null && authorization.contains(jwtBearer)) {
            var token = authorization.replace(jwtBearer + " ", "");
            var username = Authentication.getUsernameFromBearerToken(token);
            jwtPayload.setUsername(username);
        } else if (authorization != null && authorization.contains(jwtOAuth2)) {
            var token = authorization.replace(jwtOAuth2 + " ", "");
            var username = Authentication.getUsernameFromBearerToken(token);
            jwtPayload.setUsername(username);
        }
    }
}
