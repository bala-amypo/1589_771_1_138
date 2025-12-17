package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "digital_key_id" , nullable = false)
    private DigitalKey digitalKey;
    @ManyToOne
    @JoinColumn(name = "guest_id" , nullable = false)
    private Guest guest;
    private LocalDateTime accessTime;
    private String result;
    private String reason;
    public AccessLog(){}
    public AccessLog(DigitalKey digitalKey, Guest guest, LocalDateTime accessTime, String result, String reason) {
        this.digitalKey = digitalKey;
        this.guest = guest;
        setAccessTime(accessTime);
        this.result = result;
        this.reason = reason;
    }
    public Long getId() {
        return id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public Guest getGuest() {
        return guest;
    }
    public LocalDateTime getAccessTime() {
        return accessTime;
    }
    public String getResult() {
        return result;
    }
    public String getReason() {
        return reason;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setAccessTime(LocalDateTime accessTime) {
        if(accessTime.isAfter(LocalDateTime.now())){
            throw new IllegalArgumentException("Access time cannot be in the future");
        }
        this.accessTime = accessTime;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    

}
