package com.nugroho.spring.api.applications.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nugroho.spring.api.utility.Global;
import com.nugroho.spring.api.utility.ResponseFail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class HandlerGlobalFilter extends OncePerRequestFilter {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private HeaderFilter headerFilter;

    @Autowired
    private RouteFilter routeFilter;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        try {
            headerFilter.doFilter(request);
            jwtFilter.doFilter(request);
            routeFilter.doFilter(request);
            filterChain.doFilter(request, response);
        } catch (AccessDeniedException err) {
            var res = new ResponseFail();
            res.setMessage(err.getMessage());
            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(Global.convertObjectToJson(res));
        }

    }
}