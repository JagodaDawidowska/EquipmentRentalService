package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RentingResponse {
    private MessageEnum meassage;

    public RentingResponse(MessageEnum meassage) {
        this.meassage = meassage;
    }

    public MessageEnum getMeassage() {
        return meassage;
    }

    public void setMeassage(MessageEnum meassage) {
        this.meassage = meassage;
    }
}
