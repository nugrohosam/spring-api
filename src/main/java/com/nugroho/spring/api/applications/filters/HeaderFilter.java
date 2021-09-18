package com.nugroho.spring.api.applications.filters;

import javax.servlet.http.HttpServletRequest;

import com.nugroho.spring.api.applications.requests.HeaderPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeaderFilter {
    
    @Autowired
    private HeaderPayload headersPayload;

    public void doFilter(HttpServletRequest request) {

        headersPayload.setAuthorization(request.getHeader(HeaderPayload.KEY_AUTHORIZATION));
        headersPayload.setPlatform(request.getHeader(HeaderPayload.KEY_PLATFORM));
        headersPayload.setUtc(request.getHeader(HeaderPayload.KEY_TIME_UTC));
        headersPayload.setRole(request.getHeader(HeaderPayload.KEY_ROLE));

    }
}
