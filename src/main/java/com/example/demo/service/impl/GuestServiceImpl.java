// package com.example.demo.service.impl;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class GuestServiceImpl implements GuestService {
//     @Autowired
//     private GuestRepository guestRepository;
//     @Override
//     public Guest createGuest(Guest guest) {
//         Optional<Guest> existingGuest = guestRepository.findByEmail(guest.getEmail());
//         if (existingGuest.isPresent()) {
//             throw new RuntimeException("Guest with this email already exists.");
//         }
//         return guestRepository.save(guest);
//     }
//     @Override
//     public Guest updateGuest(Long id, Guest guest) {
//         Guest existing = getGuestById(id);
//         existing.setName(guest.getName());
//         existing.setEmail(guest.getEmail());
//         existing.setPhoneNumber(guest.getPhoneNumber());
//         return guestRepository.save(existing);
//     }
//     @Override
//     public Guest getGuestById(Long id) {
//         return guestRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Guest not found with id: " + id));
//     }
//     @Override
//     public List<Guest> getAllGuests() {
//         return guestRepository.findAll();
//     }
//     @Override
//     public void deactivateGuest(Long id) {
//         Guest guest = getGuestById(id);
//         guest.setActive(false); 
//         guestRepository.save(guest);
//     }
// }

