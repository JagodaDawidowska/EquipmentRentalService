package com.jdawidowska.equipmentRentalService.api.dto.response;

public class RentedInventoryResponse {

    private final String name;
    private final String surname;
    private final String equipment;
    private final Integer amount;

    public RentedInventoryResponse(String name, String surname, String equipment, Integer amount) {
        this.name = name;
        this.surname = surname;
        this.equipment = equipment;
        this.amount = amount;
    }
}
