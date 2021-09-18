package com.nugroho.spring.api.kernel.configs;

import com.nugroho.spring.api.applications.filters.HandlerGlobalFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableWebSecurity(debug = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HandlerGlobalFilter handlerFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(handlerFilter, SessionManagementFilter.class);
        http.csrf().disable().cors().and().authorizeRequests().anyRequest().permitAll();
    }
}