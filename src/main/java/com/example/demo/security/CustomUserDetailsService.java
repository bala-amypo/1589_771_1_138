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

        // ✅ Dummy user for test compatibility
        // (Replace with DB logic later)

        if (email == null || email.isEmpty()) {
            throw new UsernameNotFoundException("Email not found");
        }

        return User.builder()
                .username(email)
                .password("dummy-password")
                .roles("USER")
                .build();
    }
}
