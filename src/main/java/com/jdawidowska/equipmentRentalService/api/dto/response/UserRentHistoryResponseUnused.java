package com.jdawidowska.equipmentRentalService.api.dto.response;

import java.util.Date;

public class UserRentHistoryResponseUnused {

    private String itemName;
    private Date rentDate;
    private Date returnDate;
    private Long idUser;

    public UserRentHistoryResponseUnused(Long idUser, String itemName, Date rentDate, Date returnDate) {
        this.itemName = itemName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.idUser = idUser;
    }

    public UserRentHistoryResponseUnused() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
