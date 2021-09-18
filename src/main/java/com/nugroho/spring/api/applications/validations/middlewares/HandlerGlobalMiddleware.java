package com.nugroho.spring.api.applications.validations.middlewares;

import java.io.Serializable;

import com.nugroho.spring.api.global.Middleware;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class HandlerGlobalMiddleware implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication auth, Object nameFunction, Object param) {
        System.out.println("here middleware " + param.toString());
        String[] type = param.toString().split(Middleware.SEPARATOR);
        String typeOfCheck = type[0];

        switch (typeOfCheck) {
            case Middleware.CHECK_ROLE:
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String arg2, Object arg3) {
        return false;
    }

}
