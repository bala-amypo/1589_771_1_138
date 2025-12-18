package com.example.demo.repository;

import com.example.demo.model.RoomBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

    /**
     * Finds all bookings associated with a specific guest ID.
     * Spring Data JPA automatically parses this method name to create 
     * the SQL: SELECT * FROM room_bookings WHERE guest_id = ?
     */
    List<RoomBooking> findByGuestId(Long guestId);
    
    /**
     * Optional: Finds only active bookings for a guest.
     */
    List<RoomBooking> findByGuestIdAndActiveTrue(Long guestId);

    /**
     * Optional: Finds bookings by room number.
     */
    List<RoomBooking> findByRoomNumber(String roomNumber);
}