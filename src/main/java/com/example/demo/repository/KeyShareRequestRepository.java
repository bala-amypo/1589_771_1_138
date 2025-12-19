package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeyShareRequestRepository extends JpaRepository<KeyShareRequest, Long> {

    /**
     * Finds all share requests initiated by a specific guest.
     * The underscore allows navigation to the nested ID property.
     */
    List<KeyShareRequest> findBySharedBy_Id(Long guestId);

    /**
     * Finds all share requests received by a specific guest.
     */
    List<KeyShareRequest> findBySharedWith_Id(Long guestId);
}