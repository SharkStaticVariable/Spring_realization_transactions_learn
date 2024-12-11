package org.example.spring_realization_transactions.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RolesEntity implements GrantedAuthority//
{
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
