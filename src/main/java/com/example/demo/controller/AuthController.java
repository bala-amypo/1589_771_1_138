package com.example.demo.controller;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private GuestService guestService;

    @PostMapping("/register")
    public ResponseEntity<Guest> register(@RequestBody Guest guest) {
        // Matches your GuestServiceImpl.createGuest(guest)
        return ResponseEntity.ok(guestService.createGuest(guest));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        // Uses the login logic added to the service
        Guest guest = guestService.loginGuest(credentials.get("email"), credentials.get("password"));
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful");
        response.put("guest", guest);
        response.put("token", "dummy-jwt-token-for-now"); // Placeholder for JWT

        return ResponseEntity.ok(response);
    }
}