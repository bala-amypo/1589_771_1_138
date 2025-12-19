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
        // Validate that all entities exist
        DigitalKey key = keyRepo.findById(request.getDigitalKey().getId())
            .orElseThrow(() -> new RuntimeException("Key not found"));
        Guest sender = guestRepo.findById(request.getSharedBy().getId())
            .orElseThrow(() -> new RuntimeException("Sender not found"));
        Guest receiver = guestRepo.findById(request.getSharedWith().getId())
            .orElseThrow(() -> new RuntimeException("Receiver not found"));

        request.setDigitalKey(key);
        request.setSharedBy(sender);
        request.setSharedWith(receiver);

        return repository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest request = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Request not found"));
        request.setStatus(status);
        return repository.save(request);
    }

    @Override
    public List<KeyShareRequest> getSharedBy(Long guestId) {
        return repository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getSharedWith(Long guestId) {
        return repository.findBySharedWithId(guestId);
    }

    @Override
    public KeyShareRequest getRequestById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }
}