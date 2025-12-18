package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;
import java.util.List;

public interface KeyShareRequestService {
    KeyShareRequest createShareRequest(KeyShareRequest request);
    KeyShareRequest updateStatus(Long requestId, String status);
    
    KeyShareRequest getRequestById(Long id); 
    List<KeyShareRequest> getSharedBy(Long guestId);
    List<KeyShareRequest> getSharedWith(Long guestId);
}