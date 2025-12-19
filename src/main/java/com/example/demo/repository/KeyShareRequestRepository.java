package com.example.demo.repository;

import com.example.demo.model.KeyShareRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface KeyShareRequestRepository extends JpaRepository<KeyShareRequest, Long> {
    
    // Uses underscore to navigate to the 'id' field inside the Guest object
    List<KeyShareRequest> findBySharedBy_Id(Long guestId);
    
    List<KeyShareRequest> findBySharedWith_Id(Long guestId);
}