package com.example.demo.service;

import com.example.demo.model.KeyShareRequest;
import java.util.List;

public interface KeyShareRequestService {

    KeyShareRequest createShareRequest(KeyShareRequest request);

    List<KeyShareRequest> getSharedBy(Long guestId);

    List<KeyShareRequest> getSharedWith(Long guestId);

    KeyShareRequest getRequestById(Long id);
}
