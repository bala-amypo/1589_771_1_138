package com.example.demo.service.impl;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Guest createGuest(Guest guest) {
        // In a real JWT app, you would encode the password here before saving
        return guestRepository.save(guest);
    }

    @Override
    public Guest loginGuest(String email, String password) {
        // Find guest by email (requires findByEmail in GuestRepository)
        Guest guest = guestRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with email: " + email));

        // Check if password matches
        if (guest.getPassword().equals(password)) {
            return guest;
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    @Override
    public Guest getGuestById(Long id) {
        return guestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public Guest updateGuest(Long id, Guest guestDetails) {
        Guest guest = getGuestById(id);
        
        guest.setFullName(guestDetails.getFullName());
        guest.setEmail(guestDetails.getEmail());
        guest.setPhoneNumber(guestDetails.getPhoneNumber());
        guest.setVerified(guestDetails.getVerified());
        guest.setRole(guestDetails.getRole());
        guest.setActive(guestDetails.getActive());

        return guestRepository.save(guest);
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest guest = getGuestById(id);
        guest.setActive(false); 
        guestRepository.save(guest);
    }

    @Override
    public void deleteGuest(Long id) {
        Guest guest = getGuestById(id);
        guestRepository.delete(guest);
    }
}