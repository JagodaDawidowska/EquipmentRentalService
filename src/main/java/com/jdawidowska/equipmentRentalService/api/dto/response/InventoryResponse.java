package com.jdawidowska.equipmentRentalService.api.dto.response;

public class InventoryResponse {

    private InventoryResponseEnum inventoryResponseEnum;

    public InventoryResponse(InventoryResponseEnum inventoryResponseEnum) {
        this.inventoryResponseEnum = inventoryResponseEnum;
    }

    public InventoryResponseEnum getInventoryEnum() {
        return inventoryResponseEnum;
    }

    public void setInventoryEnum(InventoryResponseEnum inventoryResponseEnum) {
        this.inventoryResponseEnum = inventoryResponseEnum;
    }
}
