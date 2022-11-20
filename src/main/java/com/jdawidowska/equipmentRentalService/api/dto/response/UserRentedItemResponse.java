package com.jdawidowska.equipmentRentalService.api.dto.response;

public class UserRentedItemResponse {

    private final Long id;
    private final String name;
    private final Integer amount;

    public UserRentedItemResponse(Long id, String name, Integer amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }
}
