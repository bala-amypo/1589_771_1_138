package com.example.demo.service;
import java.util.*;
public class DigitalKeyService {
    DigitalKey generateKey(Long bookingId);
    DigitalKey getKeyById(Long id);
    DigitalKey getActiveKeyForBooking(Long bookingId);
    List<DigitalKey> getKeysForGuest(Long guestId);
    
}
