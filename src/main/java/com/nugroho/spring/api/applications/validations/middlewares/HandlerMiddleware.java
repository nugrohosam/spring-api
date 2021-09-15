package com.nugroho.spring.api.applications.validations.middlewares;

import java.io.Serializable;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class HandlerMiddleware implements PermissionEvaluator  {

    @Override
    public boolean hasPermission(Authentication arg0, Object arg1, Object arg2) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication arg0, Serializable arg1, String arg2, Object arg3) {
        return false;
    }
    
    public boolean hasRole(Authentication arg0, Object arg1, Object arg2) {
        return true;
    }

}
