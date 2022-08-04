package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RenteringResponse {
    public MessageEnum meassage;

    public RenteringResponse(MessageEnum meassage) {
        this.meassage = meassage;
    }

    public MessageEnum getMeassage() {
        return meassage;
    }

    public void setMeassage(MessageEnum meassage) {
        this.meassage = meassage;
    }
}
