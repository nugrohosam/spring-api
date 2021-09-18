package com.nugroho.spring.api.applications.validations.middlewares;

import java.io.Serializable;

import com.nugroho.spring.api.applications.requests.HeaderPayload;
import com.nugroho.spring.api.applications.requests.JwtPayload;
import com.nugroho.spring.api.global.Middleware;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class HandlerGlobalMiddleware implements PermissionEvaluator {

    @Autowired
    private CheckRoleMiddleware checkRole;

    @Autowired
    private CheckPermissionMiddleware checkPermission;

    @Autowired
    private JwtPayload jwtPayload;

    @Autowired
    private HeaderPayload headerPayload;

    @Override
    public boolean hasPermission(Authentication auth, Object nameFunction, Object param) {
        System.out.println("here middleware " + param.toString());
        Middleware.parseParam(param);
        String typeOfCheck = Middleware.key;

        switch (typeOfCheck) {
            case Middleware.CHECK_PERMISSION:
                var permission = Middleware.permission;
                return checkPermission.check(jwtPayload.getId(), permission);
            case Middleware.CHECK_ROLE:
                var role = Middleware.role;
                return checkRole.check(jwtPayload.getId(), role);
            case Middleware.CHECK_IS_MY_ROLE:
                return checkRole.check(jwtPayload.getId(), headerPayload.getRole());
            default:
                return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String arg2, Object arg3) {
        return false;
    }

}
