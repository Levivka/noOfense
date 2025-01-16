package com.example.noofense.Models.Dto;

public class BidDto {
    private String bidId;

    private String carNumber;

    private String status;

    private String description;

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBidId() {
        return bidId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }
}
