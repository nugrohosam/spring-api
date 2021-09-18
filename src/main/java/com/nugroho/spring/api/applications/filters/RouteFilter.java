package com.nugroho.spring.api.applications.filters;

import javax.servlet.http.HttpServletRequest;

import com.nugroho.spring.api.applications.requests.JwtPayload;
import com.nugroho.spring.api.global.Routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
public class RouteFilter {

    @Autowired
    private JwtPayload jwtPayload;

    private boolean allowToPass = true;

    private final String[] routeNeedAuth = { Routes.BOOKS, Routes.AUTHORS };

    public void doFilter(HttpServletRequest request) throws AccessDeniedException {
        boolean isContainsApi = request.getRequestURI().contains(Routes.API);
        boolean isNeedAuth = isNeedAuth(request);
        if (isNeedAuth && jwtPayload.getUsername() == null) {
            allowToPass = false;
        }

        if (!allowToPass || !isContainsApi) {
            throw new AccessDeniedException("Cannot access");
        }
    }

    private boolean isNeedAuth(HttpServletRequest request) {
        for (String route : routeNeedAuth) {
            if (request.getRequestURI().contains(route)) {
                // TODO Here to check jwt...
                return true;
            }
        }

        return false;
    }
}
