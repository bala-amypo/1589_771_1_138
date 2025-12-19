package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {

    private final KeyShareRequestService keyShareRequestService;

    public KeyShareRequestController(KeyShareRequestService keyShareRequestService) {
        this.keyShareRequestService = keyShareRequestService;
    }

    @PostMapping
    public ResponseEntity<KeyShareRequest> createShareRequest(@RequestBody KeyShareRequest request) {
        return new ResponseEntity<>(keyShareRequestService.createShareRequest(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<KeyShareRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(keyShareRequestService.updateStatus(id, status));
    }

    @GetMapping("/shared-with/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getSharedWith(@PathVariable Long guestId) {
        return ResponseEntity.ok(keyShareRequestService.getSharedWith(guestId));
    }
}