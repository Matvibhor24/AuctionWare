package com.example.auctionware;
public class Item {
    private String name;
    private String description;
    private double currBid;
    private double prevBid;

    public Item() {
        // Default constructor required for Firebase
    }

    public Item(String name, String description, double currBid, double prevBid) {
        this.name = name;
        this.description = description;
        this.currBid = currBid;
        this.prevBid = prevBid;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrBid(double currBid) {
        this.currBid=currBid;
    }
    public Double getCurrBid() {
        return currBid;
    }

    public void setPrevBid(double prevBid) {
        this.prevBid=prevBid;
    }
    public Double getPrevBid() {
        return prevBid;
    }
}
