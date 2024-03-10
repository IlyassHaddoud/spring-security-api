package com.demo.api;

import jakarta.servlet.FilterChain;
import org.apache.coyote.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(
                        request->{
                            request.requestMatchers("/").permitAll();
                            request.requestMatchers("/error").permitAll();
                            request.requestMatchers("/favicon.ico").permitAll();
                            request.anyRequest().authenticated();
                        }
                )
                .formLogin(withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new InMemoryUserDetailsManager(
                User
                        .builder()
                        .username("ilyass")
                        .password("{noop}ilyass20h")
                        .authorities("ROLE_USER")
                        .build()
        );
    }
}
