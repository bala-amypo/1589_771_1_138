package com.example.demo.model;
import java.sql.Timestamp;
public class RoomBooking{
    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean verified;
    private Boolean active;
    private String role;
    private Timestamp createdAt;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getfullName(){
        return fullName;
    }
    public void setfullName(String fullName){
        this.fullName = fullName;
    }
    public String getemail(){
        return email;
    }
    public void setemail(String email){
        this.email = email;
    }
    public String getphoneNumber(){
        return phoneNumber;
    }
    public void setphoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public Boolean getverified(){
        return verified;
    }
    public void setverified(Boolean verified){
        this.verified = verified;
    }
    public Boolean getactive(){
        return active;
    }
    public void setactive(Boolean active){
        this.active = active;
    }
    public String getrole(){
        return role;
    }
    public void setrole(String role){
        this.role = role;
    }
    public Timestamp getcreatedAt(){
        return createdAt;
    }
    public void setcreatedAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }



}