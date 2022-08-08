package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RentingResponse {
    private MessageEnum message;

    public RentingResponse(MessageEnum message) {
        this.message = message;
    }

    public MessageEnum getMessage() {
        return message;
    }

    public void setMessage(MessageEnum message) {
        this.message = message;
    }
}
