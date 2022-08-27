package com.jdawidowska.equipmentRentalService.api.dto.response;

public class InventoryResponse {
    private InventoryEnum inventoryEnum;

    public InventoryResponse(InventoryEnum inventoryEnum) {
        this.inventoryEnum = inventoryEnum;
    }

    public InventoryEnum getInventoryEnum() {
        return inventoryEnum;
    }

    public void setInventoryEnum(InventoryEnum inventoryEnum) {
        this.inventoryEnum = inventoryEnum;
    }
}
