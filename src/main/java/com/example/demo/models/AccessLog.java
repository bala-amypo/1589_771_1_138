package com.example.demo.model;
import jakarta.persistence.*;
import java.security.Timestamp;
@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "digital_key_id" , nullable = false)
    private DigitalKey digitalKey;
    @ManyToOne
    @JoinColumn(name = "guest_id" , nullable = false)
    private Guest guest;
    private Timestamp accessTime;
    private String result;
    private String reason;
    public Accesslog(){}
    public AccessLog(DigitalKey digitalKey, Guest guest, Timestamp accessTime, String result, String reason) {
        this.digitalKey = digitalKey;
        this.guest = guest;
        this.accessTime = accessTime;
        this.result = result;
        this.reason = reason;
    }
    public long getId() {
        return id;
    }
    public DigitalKey getDigitalKey() {
        return digitalKey;
    }
    public Guest getGuest() {
        return guest;
    }
    public Timestamp getAccessTime() {
        return accessTime;
    }
    public String getResult() {
        return result;
    }
    public String getReason() {
        return reason;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setDigitalKey(DigitalKey digitalKey) {
        this.digitalKey = digitalKey;
    }
    public void setGuest(Guest guest) {
        this.guest = guest;
    }
    public void setAccessTime(Timestamp accessTime) {
        this.accessTime = accessTime;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    

}
