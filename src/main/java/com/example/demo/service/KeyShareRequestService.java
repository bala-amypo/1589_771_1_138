package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class KeyShareRequestService {

    private final KeyShareRequestRepository repository;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;

    public KeyShareRequestService(KeyShareRequestRepository repository, 
                                 DigitalKeyRepository keyRepo, 
                                 GuestRepository guestRepo) {
        this.repository = repository;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
    }

    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        DigitalKey key = keyRepo.findById(request.getDigitalKey().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Key not found"));
            
        Guest sender = guestRepo.findById(request.getSharedBy().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
            
        Guest receiver = guestRepo.findById(request.getSharedWith().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Receiver not found"));

        request.setDigitalKey(key);
        request.setSharedBy(sender);
        request.setSharedWith(receiver);
        
        if (request.getStatus() == null) {
            request.setStatus("PENDING");
        }

        return repository.save(request);
    }

    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest request = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setStatus(status);
        return repository.save(request);
    }

    public KeyShareRequest getRequestById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
    }

    public java.util.List<KeyShareRequest> getSharedBy(Long guestId) {
        return repository.findBySharedById(guestId);
    }

    public java.util.List<KeyShareRequest> getSharedWith(Long guestId) {
        return repository.findBySharedWithId(guestId);
    }
}