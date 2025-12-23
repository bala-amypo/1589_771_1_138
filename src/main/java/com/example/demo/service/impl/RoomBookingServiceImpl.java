package com.example.demo.service.impl;

import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    @Autowired
    private RoomBookingRepository bookingRepository;

    @Override
    @Transactional
    public RoomBooking createBooking(RoomBooking booking) {
        validateDates(booking.getCheckInDate(), booking.getCheckOutDate());
        return bookingRepository.save(booking);
    }

    @Override
    @Transactional
    public RoomBooking updateBooking(Long id, RoomBooking bookingDetails) {
        RoomBooking existingBooking = getBookingById(id);
        
        validateDates(bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate());

        existingBooking.setGuest(bookingDetails.getGuest());
        existingBooking.setRoomNumber(bookingDetails.getRoomNumber());
        existingBooking.setCheckInDate(bookingDetails.getCheckInDate());
        existingBooking.setCheckOutDate(bookingDetails.getCheckOutDate());
        existingBooking.setRoommates(bookingDetails.getRoommates());
        existingBooking.setActive(bookingDetails.getActive());

        return bookingRepository.save(existingBooking);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return bookingRepository.findByGuestId(guestId);
    }

    @Override
    @Transactional
    public void deactivateBooking(Long id) {
        RoomBooking booking = getBookingById(id);
        booking.setActive(false);
        bookingRepository.save(booking);
    }

    private void validateDates(LocalDate checkIn, LocalDate checkOut) {
        if (checkIn == null || checkOut == null) {
            throw new IllegalArgumentException("Dates cannot be null.");
        }
        if (checkIn.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Check-in date cannot be in the past.");
        }
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Check-out date must be after check-in date.");
        }
    }
}