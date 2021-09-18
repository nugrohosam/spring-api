package com.nugroho.spring.api.applications.validations.middlewares;

import org.springframework.stereotype.Component;

@Component
public class CheckPermissionMiddleware {

    public boolean check(Long id, String role) {
        return true;
    }

    public boolean check(String username, String role) {
        return true;
    }
}
