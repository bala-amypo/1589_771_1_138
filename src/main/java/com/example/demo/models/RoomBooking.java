package com.example.demo.model;

import java.time.LocalDate;
@Entity
public class RoomBooking {
    private long id;
    private Guest guest;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOuDate;
    private Boolean active;
    private Guest roommates;
    public RoomBooking(Guest guest, String roomNumber, LocalDate checkInDate, LocalDate checkOuDate, Boolean active,
            Guest roommates) {
        this.guest = guest;
        this.roomNumber = roomNumber;
        this.checkInDate = checkInDate;
        this.checkOuDate = checkOuDate;
        this.active = active;
        this.roommates = roommates;
    }
    public long getId() {
        return id;
    }
    public Guest getGuest() {
        return guest;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public LocalDate getCheckOuDate() {
        return checkOuDate;
    }
    public Boolean getActive() {
        return active;
    }
    public Guest getRoommates() {
        return roommates;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }
    public void setCheckOuDate(LocalDate checkOuDate) {
        this.checkOuDate = checkOuDate;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
    public void setRoommates(Guest roommates) {
        this.roommates = roommates;
    }
    
}
