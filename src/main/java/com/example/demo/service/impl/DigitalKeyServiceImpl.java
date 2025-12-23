
package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    @Autowired
    private DigitalKeyRepository keyRepository;

    @Autowired
    private RoomBookingRepository bookingRepository;

    @Override
    @Transactional
    public DigitalKey generateKey(Long bookingId) {
        RoomBooking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getActive()) {
            throw new IllegalStateException("Cannot generate a key for an inactive booking.");
        }

        DigitalKey key = new DigitalKey();
        key.setBooking(booking);
        key.setKeyValue(UUID.randomUUID().toString());
        key.setIssuedAt(Timestamp.from(Instant.now()));
        key.setExpiresAt(Timestamp.from(Instant.now().plusSeconds(86400))); 
        key.setActive(true);

        return keyRepository.save(key);
    }

    @Override
    public DigitalKey getKeyById(Long id) {
        return keyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Key not found with id: " + id));
    }

    @Override
    public DigitalKey getActiveKeyForBooking(Long bookingId) {
        return keyRepository.findByBookingIdAndActiveTrue(bookingId)
                .orElseThrow(() -> new RuntimeException("No active key found for this booking"));
    }

    @Override
    public List<DigitalKey> getKeysForGuest(Long guestId) {
        return keyRepository.findKeysByGuestId(guestId);
    }
}