package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    @Autowired
    private KeyShareRequestRepository repository;

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        // Validation logic as required by documentation
        if (request.getShareStart() == null || request.getShareEnd() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dates are required");
        }
        
        // Ensure chronological order of dates
        if (request.getShareStart().after(request.getShareEnd())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Start date cannot be after end date");
        }

        // Validate that both users exist in the request
        if (request.getSharedBy() == null || request.getSharedWith() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "SharedBy and SharedWith guests are required");
        }
        
        return repository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {
        KeyShareRequest request = getRequestById(requestId);
        request.setStatus(status);
        return repository.save(request);
    }

    @Override
    public KeyShareRequest getRequestById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Request not found"));
    }

    @Override
    public List<KeyShareRequest> getSharedBy(Long guestId) {
        // Matches the updated repository method name
        return repository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getSharedWith(Long guestId) {
        // Matches the updated repository method name
        return repository.findBySharedWithId(guestId);
    }
}