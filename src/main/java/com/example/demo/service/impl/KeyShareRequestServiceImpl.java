package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repository;
    private final DigitalKeyRepository keyRepo;
    private final GuestRepository guestRepo;

    // Constructor Injection
    public KeyShareRequestServiceImpl(KeyShareRequestRepository repository, 
                                     DigitalKeyRepository keyRepo, 
                                     GuestRepository guestRepo) {
        this.repository = repository;
        this.keyRepo = keyRepo;
        this.guestRepo = guestRepo;
    }

    @Override
    @Transactional
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        // 1. Fetch and Validate Digital Key
        DigitalKey key = keyRepo.findById(request.getDigitalKey().getId())
            .orElseThrow(() -> new RuntimeException("Digital Key ID " + request.getDigitalKey().getId() + " not found"));

        // 2. Fetch and Validate Sender
        Guest sender = guestRepo.findById(request.getSharedBy().getId())
            .orElseThrow(() -> new RuntimeException("Sender Guest ID " + request.getSharedBy().getId() + " not found"));

        // 3. Fetch and Validate Receiver
        Guest receiver = guestRepo.findById(request.getSharedWith().getId())
            .orElseThrow(() -> new RuntimeException("Receiver Guest ID " + request.getSharedWith().getId() + " not found"));

        // 4. Map the full objects back to the request
        request.setDigitalKey(key);
        request.setSharedBy(sender);
        request.setSharedWith(receiver);

        // 5. Save and return (JSON annotations in Entity handle the response)
        return repository.save(request);
    }

    @Override
    public List<KeyShareRequest> getSharedBy(Long guestId) {
        return repository.findBySharedBy_Id(guestId);
    }

    @Override
    public List<KeyShareRequest> getSharedWith(Long guestId) {
        return repository.findBySharedWith_Id(guestId);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest request = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Share Request not found with ID: " + id));
        request.setStatus(status);
        return repository.save(request);
    }

    @Override
    public KeyShareRequest getRequestById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Share Request not found with ID: " + id));
    }
}