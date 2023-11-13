package com.example.ceilingontop;

import java.util.List;

public class house {
    private String sellerId;
    private String houseName;
    private String houseAddress;
    private String phoneNumber;
    private String numRooms;
    private String numWashrooms;
    private String houseImages;

    public house() {
        // Default constructor required for Firebase
    }

    public house( String houseName,String houseAddress,  String numRooms,String numWashrooms,String phoneNumber, String houseImages) {
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.numRooms = numRooms;
        this.numWashrooms = numWashrooms;
        this.phoneNumber = phoneNumber;
        this.houseImages = houseImages;
    }

    public String getHouseId() {
        return sellerId;
    }

    public void setHouseId(String houseId) {
        this.sellerId = houseId;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String  getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(String numRooms) {
        this.numRooms = numRooms;
    }

    public String  getNumWashrooms() {
        return numWashrooms;
    }

    public void setNumWashrooms(String numWashrooms) {
        this.numWashrooms = numWashrooms;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public String getHouseImages() {
        return houseImages;
    }

    public void setHouseImages(String houseImages) {
        this.houseImages = houseImages;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
