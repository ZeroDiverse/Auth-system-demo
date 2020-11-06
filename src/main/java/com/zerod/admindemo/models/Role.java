package com.zerod.admindemo.models;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.zerod.admindemo.models.Authority.*;

public enum Role {
    BOARD_OF_DIRECTOR(Sets.newHashSet(BOD_READ, BOD_WRITE, HEAD_READ, HEAD_WRITE, DEVELOPER_READ, DEVELOPER_WRITE)),
    HEAD_OF_MANAGEMENT(Sets.newHashSet(DEVELOPER_READ, DEVELOPER_WRITE)),
    RECRUITER(Sets.newHashSet(APPLICATION_READ, APPLICATION_WRITE)),
    DEVELOPER(Sets.newHashSet());

    private final Set<Authority> authorities;

    Role(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }
}
