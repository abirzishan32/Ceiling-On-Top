package com.example.ceilingontop;

import java.util.List;

public class house {
    private String sellerId;
    private String houseName;
    private String houseAddress;
    private int numRooms;
    private int numWashrooms;
    private List <String> houseImages;

    public house() {
        // Default constructor required for Firebase
    }

    public house( String houseName,String houseAddress,  int numRooms,int numWashrooms, String houseId) {
        this.sellerId = houseId;
        this.houseName = houseName;
        this.houseAddress = houseAddress;
        this.numRooms = numRooms;
        this.numWashrooms = numWashrooms;
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

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    public int getNumWashrooms() {
        return numWashrooms;
    }

    public void setNumWashrooms(int numWashrooms) {
        this.numWashrooms = numWashrooms;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerId() {
        return sellerId;
    }
}
