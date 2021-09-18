package com.nugroho.spring.api.applications.requests;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Data
@Component
@RequestScope
public class JwtPayload {
    private Long id;
    private String name;
    private String username;
    private String role;
}
