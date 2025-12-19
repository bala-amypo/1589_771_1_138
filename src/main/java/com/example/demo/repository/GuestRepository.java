package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Guest;
import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    
    // Checks if email exists (useful for registration validation)
    boolean existsByEmail(String email);

    // Retrieves the Guest object by email (required for Login logic)
    Optional<Guest> findByEmail(String email);
}