package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RentingResponse {

    private RentingEnum message;

    public RentingResponse(RentingEnum message) {
        this.message = message;
    }

    public RentingEnum getMessage() {
        return message;
    }

    public void setMessage(RentingEnum message) {
        this.message = message;
    }
}
