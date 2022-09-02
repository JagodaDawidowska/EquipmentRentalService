package com.jdawidowska.equipmentRentalService.api.dto.request;

public class ReturnRequest {

    private long idRentedInventory;
    private String feedback;

    public ReturnRequest(long idRentedInventory, String feedback) {
        this.idRentedInventory = idRentedInventory;
        this.feedback = feedback;
    }

    public long getIdRentedInventory() {
        return idRentedInventory;
    }

    public void setIdRentedInventory(long idRentedInventory) {
        this.idRentedInventory = idRentedInventory;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}