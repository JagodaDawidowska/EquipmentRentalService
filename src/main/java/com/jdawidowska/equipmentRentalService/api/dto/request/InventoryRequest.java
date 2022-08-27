package com.jdawidowska.equipmentRentalService.api.dto.request;

import com.jdawidowska.equipmentRentalService.model.EquipmentEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class InventoryRequest {

    @Enumerated(EnumType.STRING)
    private  EquipmentEnum itemName;
    private  Integer totalAmount;
    private  Integer availableAmount;
    private Long idUser;
    private static Long idItem;

    public InventoryRequest(EquipmentEnum itemName, Integer totalAmount, Integer availableAmount, Long idUser, Long idItem) {
        this.itemName = itemName;
        this.totalAmount = totalAmount;
        this.availableAmount = availableAmount;
        this.idUser = idUser;
        this.idItem = idItem;
    }


    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public static Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public EquipmentEnum getItemName() {
        return itemName;
    }

    public void setItemName(EquipmentEnum itemName) {
        this.itemName = itemName;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(Integer availableAmount) {
        this.availableAmount = availableAmount;
    }

    @Override
    public String toString() {
        return "InventoryRequest{" +
                "itemName=" + itemName +
                ", totalAmount=" + totalAmount +
                ", availableAmount=" + availableAmount +
                '}';
    }
}
