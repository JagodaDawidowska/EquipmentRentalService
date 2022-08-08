package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RentingResponse {
    private MessageEnum message;

    public RentingResponse(MessageEnum meassage) {
        this.message = meassage;
    }

    public MessageEnum getMeassage() {
        return message;
    }

    public void setMeassage(MessageEnum meassage) {
        this.message = meassage;
    }
}
