package com.demo.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;
import java.util.Collections;

public class RobotAuthentication implements Authentication {

    private boolean isAuthenticated;
    private Collection<? extends GrantedAuthority> authorities;
    private final String password;

    private  RobotAuthentication(String password,Collection<? extends GrantedAuthority> authorities) {
        this.password = password;
        this.authorities = authorities;
        this.isAuthenticated = password == null;
    }

    public static RobotAuthentication unauthenticated(String password)
    {
        return new RobotAuthentication(password, Collections.emptyList());
    }

    public static RobotAuthentication authenticated()
    {
        return new RobotAuthentication(null,AuthorityUtils.createAuthorityList("ROLE_ROBOT"));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.getName();
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("just dont pls");
    }

    @Override
    public String getName() {
        return "Mr Robot 🤖";
    }

    public String getPassword()
    {
        return this.password;
    }
}
