package com.example.auctionware;
public class Item {
    private String id;
    private String name;
    private String description;
    private double currBid;
    private double minBid;
    private String imageUrl;
    private String prevBidder;

    public Item() {
        // Default constructor required for Firebase
    }

    public Item(String id, String name, String description, double currBid, double minBid, String prevBidder, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currBid = currBid;
        this.minBid = minBid;
        this.prevBidder = prevBidder;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getName() {
        return name;
    }
    public String getId() {return id;}

    public void setName(String name) {
        this.name = name;
    }
    public void setId(String id) {this.id = id;}

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

    public void setMinBid(double minBid) {
        this.minBid=minBid;
    }
    public Double getMinBid() {
        return minBid;
    }

    public String getPrevBidder() {
        return prevBidder;
    }

    public void setPrevBidder(String prevBidder) {
        this.prevBidder = prevBidder;
    }
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
