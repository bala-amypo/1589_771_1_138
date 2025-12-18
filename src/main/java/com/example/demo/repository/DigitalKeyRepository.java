package com.example.demo.repository;

import com.example.demo.model.DigitalKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    // Find the current active key for a specific booking
    Optional<DigitalKey> findByBookingIdAndActiveTrue(Long bookingId);

    // Custom query to find keys through the booking's guest association
    @Query("SELECT dk FROM DigitalKey dk WHERE dk.booking.guest.id = :guestId")
    List<DigitalKey> findKeysByGuestId(@Param("guestId") Long guestId);
}