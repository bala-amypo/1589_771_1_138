package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.DigitalKeyService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    @Autowired
    private DigitalKeyRepository digitalKeyRepository;

    @Autowired
    private RoomBookingRepository roomBookingRepository;

    @Override
    public DigitalKey createKey(Long bookingId, DigitalKey digitalKey) {
        RoomBooking booking = roomBookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + bookingId));
        
        digitalKey.setBooking(booking);
        return digitalKeyRepository.save(digitalKey);
    }

    @Override
    public DigitalKey getKeyById(Long id) {
        return digitalKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DigitalKey not found with id: " + id));
    }

    @Override
    public List<DigitalKey> getAllKeys() {
        return digitalKeyRepository.findAll();
    }

    @Override
    public void deleteKey(Long id) {
        DigitalKey key = getKeyById(id);
        digitalKeyRepository.delete(key);
    }
}