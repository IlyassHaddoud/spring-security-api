package com.demo.api;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var authManager = new ProviderManager(new RobotAuthenticationProvider(List.of("beep-boop", "beep-beep")));
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
                .addFilterBefore(new RobotFilter(authManager), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(new ZedProviderProvider())
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
