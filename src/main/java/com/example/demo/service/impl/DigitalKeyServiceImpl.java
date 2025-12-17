package com.example.demo.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.UUID;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    @Autowired
    private DigitalKeyRepository keyRepository;
    
    @Autowired
    private RoomBookingRepository bookingRepository;

    @Override
    public DigitalKey generateKey(Long bookingId) {
        RoomBooking booking = bookingRepository.findById(bookingId)
             .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        if (!booking.isActive()) {
            throw new IllegalStateException("Cannot generate key for an inactive booking.");
        }

        DigitalKey newKey = new DigitalKey();
        newKey.setBooking(booking);
        newKey.setKeyCode(UUID.randomUUID().toString()); // Generate unique key code
        newKey.setActive(true);
        
        return keyRepository.save(newKey);
    }

    @Override
    public DigitalKey getKeyById(Long id) {
        return keyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Digital Key not found"));
    }

    @Override
    public DigitalKey getActiveKeyForBooking(Long bookingId) {
        return keyRepository.findByBookingIdAndIsActiveTrue(bookingId);
    }

    @Override
    public List<DigitalKey> getKeysForGuest(Long guestId) {
        return keyRepository.findByBooking_GuestId(guestId);
    }
}