package com.elearningplatformservices.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .httpBasic()
//            .and()
//                .authorizeRequests()
//                .antMatchers("/index.html", "/", "/home", "/login", "/course/**", "/instructors/**",
//                        "/contactUs/**", "/customer/**", "/user/**", "/resource/**, /registerUser/** , /registerInstructor/**, /favicon.ico/**").permitAll()
//                .anyRequest().authenticated()
//            .and()
//                .csrf()
//                .disable();
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/", "/home", "/login", "/course/**", "/instructors/**", "/contactUs/**", "/customer/**", "/user/**", "/resource/**",
                        "/registerInstructor/**", "/registerInstructor", "/registerUser", "/registerUser/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable();

    }
}
