package com.nugroho.spring.api.applications.requests;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Data
@Component
@RequestScope
public class HeaderPayload {
    public static String KEY_TIME_UTC = "Utc";
    public static String KEY_PLATFORM = "Platform";
    public static String KEY_ROLE = "Role";
    public static String KEY_AUTHORIZATION = "Authorization";

    private String utc;
    private String platform;
    private String authorization;
    private String role;
}
