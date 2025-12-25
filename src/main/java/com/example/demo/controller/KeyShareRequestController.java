package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/key-share-requests")
public class KeyShareRequestController {

    private final KeyShareRequestService keyShareRequestService;

    public KeyShareRequestController(KeyShareRequestService keyShareRequestService) {
        this.keyShareRequestService = keyShareRequestService;
    }

    // Create a key share request
    @PostMapping
    public KeyShareRequest createShareRequest(@RequestBody KeyShareRequest request) {
        return keyShareRequestService.createShareRequest(request);
    }

    // Get a share request by ID
    @GetMapping("/{id}")
    public KeyShareRequest getRequestById(@PathVariable Long id) {
        return keyShareRequestService.getRequestById(id);
    }

    // Get all requests shared by a guest
    @GetMapping("/shared-by/{guestId}")
    public List<KeyShareRequest> getSharedBy(@PathVariable Long guestId) {
        return keyShareRequestService.getSharedBy(guestId);
    }

    // Get all requests shared with a guest
    @GetMapping("/shared-with/{guestId}")
    public List<KeyShareRequest> getSharedWith(@PathVariable Long guestId) {
        return keyShareRequestService.getSharedWith(guestId);
    }
}
