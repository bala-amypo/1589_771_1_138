package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
   
@RestController
@RequestMapping("/api/key-share")
@Tag(name = "Key Share Management", description = "Endpoints for managing key share requests")
public class KeyShareRequestController {

    @Autowired
    private KeyShareRequestService keyShareRequestService;

    @PostMapping
    public ResponseEntity<KeyShareRequest> createShareRequest(@RequestBody KeyShareRequest request) {
        return ResponseEntity.ok(keyShareRequestService.createShareRequest(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<KeyShareRequest> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(keyShareRequestService.updateStatus(id, status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyShareRequest> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(keyShareRequestService.getRequestById(id));
    }

    @GetMapping("/shared-by/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getSharedBy(@PathVariable Long guestId) {
        return ResponseEntity.ok(keyShareRequestService.getSharedBy(guestId));
    }

    @GetMapping("/shared-with/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getSharedWith(@PathVariable Long guestId) {
        return ResponseEntity.ok(keyShareRequestService.getSharedWith(guestId));
    }
}