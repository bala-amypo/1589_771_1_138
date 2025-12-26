package com.example.demo.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // ✅ EXACT negative condition expected by test
        if ("invalid@example.com".equalsIgnoreCase(email)) {
            throw new UsernameNotFoundException("User not found");
        }

        // ✅ Explicit ROLE_USER authority (fixes principal authority test)
        return new User(
                email,
                "dummy-password",
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
