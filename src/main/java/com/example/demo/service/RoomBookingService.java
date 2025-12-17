package com.example.demo.service;
import java.util.*;
public interface RoomBookingService {
    RoomBooking createBooking(RoomBooking booking);
    RoomBooking updateBooking(RoomBooking booking);
    RoomBooking getBookingById(Long id);
    List<RoomBooking> getBookingsForGuest(Long guestId);
    getBookingsForGuest(Long guestId);
    void deactivateBooking(Long id);
}
