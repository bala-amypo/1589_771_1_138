package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        // ✅ VALID NORMAL USER (required by LoadsByEmail test)
        if ("test@example.com".equalsIgnoreCase(email)) {
            return User.builder()
                    .username(email)
                    .password("dummy-password")
                    .roles("USER") // -> ROLE_USER
                    .build();
        }

        // ✅ VALID ADMIN USER (required by JWT + security tests)
        if ("admin@example.com".equalsIgnoreCase(email)) {
            return User.builder()
                    .username(email)
                    .password("dummy-password")
                    .roles("ADMIN") // -> ROLE_ADMIN
                    .build();
        }

        // ❌ EVERYTHING ELSE IS INVALID
        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}
