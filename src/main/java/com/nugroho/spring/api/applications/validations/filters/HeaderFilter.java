package com.nugroho.spring.api.applications.validations.filters;

import javax.servlet.http.HttpServletRequest;

import com.nugroho.spring.api.applications.requests.HeadersPayload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HeaderFilter {
    
    @Autowired
    private HeadersPayload headersPayload;

    public void doFilter(HttpServletRequest request) {

        headersPayload.setAuthorization(request.getHeader(HeadersPayload.KEY_AUTHORIZATION));
        headersPayload.setPlatform(request.getHeader(HeadersPayload.KEY_PLATFORM));
        headersPayload.setUtc(request.getHeader(HeadersPayload.KEY_TIME_UTC));
        headersPayload.setRole(request.getHeader(HeadersPayload.KEY_ROLE));

    }
}
