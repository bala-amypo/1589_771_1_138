package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-share")
@Tag(name = "Key Share Management", description = "Endpoints for managing key share requests")
public class KeyShareRequestController {

    private final KeyShareRequestService keyShareRequestService;

    public KeyShareRequestController(KeyShareRequestService keyShareRequestService) {
        this.keyShareRequestService = keyShareRequestService;
    }

    @PostMapping
    public ResponseEntity<KeyShareRequest> createShareRequest(
            @RequestBody KeyShareRequest request) {

        KeyShareRequest created =
                keyShareRequestService.createShareRequest(request);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<KeyShareRequest> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                keyShareRequestService.updateStatus(id, status)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<KeyShareRequest> getRequestById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                keyShareRequestService.getRequestById(id)
        );
    }
    @GetMapping("/shared-by/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getSharedBy(
            @PathVariable Long guestId) {

        return ResponseEntity.ok(
                keyShareRequestService.getSharedBy(guestId)
        );
    }
    @GetMapping("/shared-with/{guestId}")
    public ResponseEntity<List<KeyShareRequest>> getSharedWith(
            @PathVariable Long guestId) {

        return ResponseEntity.ok(
                keyShareRequestService.getSharedWith(guestId)
        );
    }
}
