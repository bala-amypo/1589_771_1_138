package com.example.demo.model;

import java.time.LocalDate;
import jakarta.persistence.*; // FIXED: Added this import to solve "cannot find symbol: class Entity"

@Entity
@Table(name = "room_bookings")
public class RoomBooking {

    @Id // FIXED: Added ID annotation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // FIXED: Added Relationship mapping
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate; // FIXED: Renamed for clarity
    private Boolean active;

    @ManyToOne // FIXED: Added Relationship mapping
    @JoinColumn(name = "roommate_id")
    private Guest roommates;

    // 1. FIXED: Added Required No-Args Constructor
    public RoomBooking() {}

    public RoomBooking(Guest guest, String roomNumber, LocalDate checkInDate, LocalDate checkOutDate, Boolean active,
                       Guest roommates) {
        this.guest = guest;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.active = active;
        this.roommates = roommates;
    }

    // --- Getters and Setters ---
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Guest getRoommates() { return roommates; }
    public void setRoommates(Guest roommates) { this.roommates = roommates; }
}