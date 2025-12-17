package com.example.demo.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    @Autowired
    private KeyShareRequestRepository shareRequestRepository;

    @Autowired
    private GuestRepository guestRepository; 
    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        boolean requesterExists = guestRepository.existsById(request.getRequester().getId());
        boolean recipientExists = guestRepository.existsById(request.getRecipient().getId());

        if (!requesterExists || !recipientExists) {
            throw new IllegalArgumentException("Invalid requester or recipient ID.");
        }
        if (request.getExpiryDate() != null && request.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Expiry date cannot be in the past.");
        }
                if (request.getStatus() == null) {
            request.setStatus("PENDING");
        }

        return shareRequestRepository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long requestId, String status) {
        KeyShareRequest request = getShareRequestById(requestId);
        request.setStatus(status);
        return shareRequestRepository.save(request);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return shareRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Share Request not found with id: " + id));
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return shareRequestRepository.findByRequesterId(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return shareRequestRepository.findByRecipientId(guestId);
    }
}

