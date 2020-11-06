package com.zerod.admindemo.security;

import com.zerod.admindemo.models.User;
import com.zerod.admindemo.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("There is not user with this email"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user));
    }

    public Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole().toString()));
    }
}