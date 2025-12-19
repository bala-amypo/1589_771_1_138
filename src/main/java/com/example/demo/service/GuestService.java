package com.example.demo.service;

import com.example.demo.model.Guest;
import java.util.List;
import java.util.Optional;

public interface GuestService {
    // For AuthController /register
    Guest createGuest(Guest guest); 

    // For AuthController /login
    Guest loginGuest(String email, String password);

    // Standard CRUD
    Guest updateGuest(Long id, Guest guest); 
    Guest getGuestById(Long id);
    List<Guest> getAllGuests();
    void deactivateGuest(Long id); 
    void deleteGuest(Long id);
}