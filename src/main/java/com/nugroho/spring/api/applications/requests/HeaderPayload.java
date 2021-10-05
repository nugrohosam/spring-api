package com.nugroho.spring.api.applications.requests;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Data
@Component
@RequestScope
public class HeaderPayload {
    public static final String KEY_TIME_UTC = "Utc";
    public static final String KEY_PLATFORM = "Platform";
    public static final String KEY_ROLE = "Role";
    public static final String KEY_AUTHORIZATION = "Authorization";

    private String utc;
    private String platform;
    private String authorization;
    private String role;

    public String getWithKey(String key) {
        switch (key) {
            case KEY_TIME_UTC:
                return this.utc;
            case KEY_PLATFORM:
                return this.platform;
            case KEY_ROLE:
                return this.authorization;
            case KEY_AUTHORIZATION:
                return this.role;
            default:
                return null;
        }
    }
}
