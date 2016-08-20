package com.bussssco.applications.buscoolversion1.model;

import android.location.Location;

import java.util.UUID;

/**
 * Created by youssef on 8/20/2016.
 */
public class Client {
    private UUID clientUUID;
    private int tripNumber;
    private String studentName;
    private String phoneNumber;
    private Location studentLocation;

    private Client(){
        this.clientUUID = UUID.randomUUID();
    }

    public static Client newClient(int tripNumber, String studentName, String phoneNumber, double latitude, double longitude){
        Client cl = new Client();
        cl.setTripNumber(tripNumber);
        cl.setStudentName(studentName);
        cl.setPhoneNumber(phoneNumber);
        cl.setStudentLocation(latitude,longitude);
        return cl;
    }

    public UUID getClientUUID() {
        return clientUUID;
    }

    public void setClientUUID(UUID clientUUID) {
        this.clientUUID = clientUUID;
    }

    public int getTripNumber() {
        return tripNumber;
    }

    public void setTripNumber(int tripNumber) {
        this.tripNumber = tripNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Location getStudentLocation() {
        return studentLocation;
    }

    public void setStudentLocation(double latitude, double longitude) {
        Location studentLocation = new Location("User_defined_Location");
        studentLocation.setLatitude(latitude);
        studentLocation.setLongitude(longitude);
        this.studentLocation = studentLocation;
        
    }

}
