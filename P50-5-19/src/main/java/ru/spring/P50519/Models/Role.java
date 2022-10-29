package ru.spring.P50519.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    STOREKEEPER,ADMIN,CHAR,SELLER,USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
