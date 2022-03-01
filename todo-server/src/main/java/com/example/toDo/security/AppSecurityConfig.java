package com.example.toDo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@Configuration
@EnableWebSecurity

public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors();
        httpSecurity.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/auth/**", "/login", "/h2-console/**", "/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger*/**", "/webjars/**").permitAll()
//                .antMatchers("/api/v1/tasks").hasAnyAuthority("USER", "ADMIN")
//                .antMatchers("/api/v1/tasks/**").hasAnyAuthority("ADMIN")
//                .anyRequest().authenticated()
        ;
        httpSecurity.headers().addHeaderWriter(
                new StaticHeadersWriter("Access-Control-Allow-Origin", "*"));

        httpSecurity.headers().frameOptions().disable();
    }




}