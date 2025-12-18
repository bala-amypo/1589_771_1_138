package com.example.demo.controller;

import com.example.demo.model.DigitalKey;
import com.example.demo.service.DigitalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/digital-keys")
public class DigitalKeyController {

    @Autowired
    private DigitalKeyService digitalKeyService;

    /**
     * POST /generate/{bookingId} - Generate a new key for a specific booking.
     * Requirement: Logic in service throws IllegalStateException if booking is inactive.
     */
    @PostMapping("/generate/{bookingId}")
    public ResponseEntity<DigitalKey> generateKey(@PathVariable Long bookingId) {
        DigitalKey newKey = digitalKeyService.generateKey(bookingId); //
        return ResponseEntity.ok(newKey);
    }

    /**
     * GET /{id} - Retrieve a specific key by its unique ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DigitalKey> getKey(@PathVariable Long id) {
        DigitalKey key = digitalKeyService.getKeyById(id); //
        return ResponseEntity.ok(key);
    }

    /**
     * GET /booking/{bookingId} - Retrieve the currently active key for a booking.
     */
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<DigitalKey> getActiveKey(@PathVariable Long bookingId) {
        DigitalKey activeKey = digitalKeyService.getActiveKeyForBooking(bookingId); //
        return ResponseEntity.ok(activeKey);
    }

    /**
     * GET /guest/{guestId} - Retrieve all keys associated with a specific guest.
     */
    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<DigitalKey>> getKeysForGuest(@PathVariable Long guestId) {
        List<DigitalKey> keys = digitalKeyService.getKeysForGuest(guestId); //
        return ResponseEntity.ok(keys);
    }
}