package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
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

        // Validate time window
        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after share start");
        }

        // Validate same guest
        if (request.getSharedBy().getId().equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be the same");
        }

        DigitalKey key = keyRepo.findById(request.getDigitalKey().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Digital key not found"));

        Guest sender = guestRepo.findById(request.getSharedBy().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sender guest not found"));

        Guest receiver = guestRepo.findById(request.getSharedWith().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Receiver guest not found"));

        request.setDigitalKey(key);
        request.setSharedBy(sender);
        request.setSharedWith(receiver);

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
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Share request not found: " + id));
    }
}
