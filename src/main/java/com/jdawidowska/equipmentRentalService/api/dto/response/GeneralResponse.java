package com.jdawidowska.equipmentRentalService.api.dto.response;

public class GeneralResponse {
    private GeneralEnum message;

    public GeneralResponse(GeneralEnum message) {
        this.message = message;
    }

    public GeneralEnum getMessage() {
        return message;
    }

    public void setMessage(GeneralEnum message) {
        this.message = message;
    }
}
