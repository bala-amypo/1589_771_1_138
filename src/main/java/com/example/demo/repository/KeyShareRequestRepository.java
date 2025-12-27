package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeyShareRequestRepository
        extends JpaRepository<KeyShareRequest, Long> {

    // ✅ REQUIRED BY EXISTING SERVICE
    List<KeyShareRequest> findBySharedById(Long guestId);

    // ✅ REQUIRED BY EXISTING SERVICE
    List<KeyShareRequest> findBySharedWithId(Long guestId);

    // ✅ REQUIRED FOR ACCESS LOG AUTHORIZATION
    boolean existsByDigitalKeyIdAndSharedWithId(
            Long digitalKeyId,
            Long sharedWithId
    );
}
