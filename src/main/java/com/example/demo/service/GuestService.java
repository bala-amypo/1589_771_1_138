package com.example.demo.service;

import com.example.demo.model.Guest;
import java.util.List;

public interface GuestService {
    Guest saveGuest(Guest guest);
    Guest getGuestById(Long id);
    List<Guest> getAllGuests();
    void deleteGuest(Long id);
}