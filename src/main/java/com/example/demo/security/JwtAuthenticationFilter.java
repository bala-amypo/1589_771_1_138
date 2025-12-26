package com.example.demo.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1️⃣ Get Authorization header
        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            // 2️⃣ Extract token
            String token = header.substring(7);

            // 3️⃣ Extract email from token
            String email = jwtTokenProvider.getEmailFromToken(token);

            // 4️⃣ Authenticate only if not already authenticated
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                // 5️⃣ Load UserDetails
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // 6️⃣ Create Authentication token (VERY IMPORTANT)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // 7️⃣ Set Authentication in SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 8️⃣ Continue filter chain
        filterChain.doFilter(request, response);
    }
}
