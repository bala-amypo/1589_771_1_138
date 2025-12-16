package com.example.demo.model;
import jakarta.persisten
import java.security.Timestamp;
@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private DigitalKey digitalKey;
    private Guest guest;
    private Timestamp accessTime;
    private String result;
    private String reason;
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
