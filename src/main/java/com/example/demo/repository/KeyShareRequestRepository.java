package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyShareRequestRepository
        extends JpaRepository<KeyShareRequest, Long> {

    boolean existsByDigitalKeyIdAndSharedWithId(
            Long digitalKeyId,
            Long sharedWithId
    );
}
