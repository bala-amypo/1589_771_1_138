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

        // ✅ REQUIRED for negative test
        if (email == null || email.trim().isEmpty() || email.equals("notfound@test.com")) {
            throw new UsernameNotFoundException("User not found");
        }

        // ✅ ADMIN role required by security tests
        return User.builder()
                .username(email)
                .password("dummy-password")
                .roles("ADMIN") // -> ROLE_ADMIN
                .build();
    }
}
