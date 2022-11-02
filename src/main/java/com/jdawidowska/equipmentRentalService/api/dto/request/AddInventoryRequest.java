package com.jdawidowska.equipmentRentalService.api.dto.request;

public class AddInventoryRequest {

    private String itemName;
    private Integer totalAmount;
    private Integer availableAmount;
    private Long idUser;
    private Long idItem;

    public AddInventoryRequest(String itemName, Integer totalAmount, Integer availableAmount, Long idUser, Long idItem) {
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

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
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
                "itemName='" + itemName + '\'' +
                ", totalAmount=" + totalAmount +
                ", availableAmount=" + availableAmount +
                ", idUser=" + idUser +
                ", idItem=" + idItem +
                '}';
    }
}
