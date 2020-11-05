package com.ess.filter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfigJwt extends ResourceServerConfigurerAdapter {

    String admin = "ROLE_admin";
    String vendor = "ROLE_vendor";
//    String client = " ROLE_client";

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/oauth/token", "/user-service/user/**").permitAll()
                .antMatchers("/api/admin/**", "/api/users/roles", "/api/products/approve", "/api/products/inactive")
                .hasAuthority(admin)
                .antMatchers(HttpMethod.POST, "/api/categories")
                .hasAuthority(admin)
                .antMatchers(HttpMethod.PUT, "/api/categories")
                .hasAuthority(admin)
                .antMatchers(HttpMethod.POST, "/api/products")
                .hasAnyAuthority(admin, vendor)
                .antMatchers(HttpMethod.PUT, "/api/products")
                .hasAnyAuthority(admin, vendor)
                .antMatchers("/api/**").authenticated()
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
}
