package com.foxminded.vitaliifedan.task10.models.persons;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    NONE, ROLE_STUFF, ROLE_STUDENT, ROLE_TEACHER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
