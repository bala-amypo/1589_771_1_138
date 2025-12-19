package com.example.demo.service.impl;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService, UserDetailsService {

    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    // Standard constructor injection (No @Autowired needed)
    public GuestServiceImpl(GuestRepository guestRepository, PasswordEncoder passwordEncoder) {
        this.guestRepository = guestRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return guestRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Guest not found with email: " + email));
    }

    @Override
    public Guest createGuest(Guest guest) {
        if (guestRepository.existsByEmail(guest.getEmail())) {
            throw new RuntimeException("Email already exists: " + guest.getEmail());
        }

        // 1. Hash the password before saving
        guest.setPassword(passwordEncoder.encode(guest.getPassword()));

        // 2. Default Rules
        if (guest.getRole() == null) {
            guest.setRole("ROLE_USER");
        }
        if (guest.getActive() == null) {
            guest.setActive(true);
        }

        return guestRepository.save(guest);
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
        
        // Re-hash password if it's being updated
        if (guestDetails.getPassword() != null && !guestDetails.getPassword().isEmpty()) {
            guest.setPassword(passwordEncoder.encode(guestDetails.getPassword()));
        }

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