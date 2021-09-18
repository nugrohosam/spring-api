package com.nugroho.spring.api.applications.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class HandlerGlobalFilter extends OncePerRequestFilter {

    @Autowired
    private HeaderFilter headerFilter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("here request filter");
        headerFilter.doFilter(request);
        filterChain.doFilter(request, response);
    }
}