package com.jdawidowska.equipmentRentalService.api.dto.request;

public class RentingRequest {

    public long idCustomer;
    public long idItem;

    public RentingRequest(long idCustomer, long idItem) {
        this.idCustomer = idCustomer;
        this.idItem = idItem;
    }

    public long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }
}
