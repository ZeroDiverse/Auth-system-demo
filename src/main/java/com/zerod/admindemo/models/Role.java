package com.zerod.admindemo.models;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.zerod.admindemo.models.Permission.*;

public enum Role {
    LV0(Sets.newHashSet(LV0_READ, LV0_WRITE, LV1_READ, LV1_WRITE, LV2_READ, LV2_WRITE)),
    LV1(Sets.newHashSet(LV2_READ, LV2_WRITE)),
    LV2(Sets.newHashSet());

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> grantedAuthorities() {
        Set<GrantedAuthority> authorities = getPermissions().stream()
                .map(perm -> new SimpleGrantedAuthority(perm.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

}
