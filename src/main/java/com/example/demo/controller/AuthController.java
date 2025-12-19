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
        // Registers a new guest/user and returns the saved object
        return ResponseEntity.ok(guestService.createGuest(guest));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        // Simulating the login logic for now since we are omitting security files
        String username = loginRequest.get("username");
        
        Map<String, String> response = new HashMap<>();
        response.add("message", "Login successful for " + username);
        response.add("token", "dummy-jwt-token-for-testing");

        return ResponseEntity.ok(response);
    }
}