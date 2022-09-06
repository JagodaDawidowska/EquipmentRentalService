package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RegisterResponse {
    private RegisterEnum registerEnum;

    public RegisterResponse(RegisterEnum registerEnum) {
        this.registerEnum = registerEnum;
    }

    public RegisterEnum getRegisterEnum() {
        return registerEnum;
    }

    public void setRegisterEnum(RegisterEnum registerEnum) {
        this.registerEnum = registerEnum;
    }
}
