package com.jdawidowska.equipmentRentalService.api.dto.response;

import java.util.Date;

public class UserRentHistoryResponse {
    private String itemName;
    private Date rentDate;
    private Date returnDate;
    private String email;

    public UserRentHistoryResponse(String email, String itemName, Date rentDate, Date returnDate) {
        this.itemName = itemName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.email = email;
    }

    public UserRentHistoryResponse() {
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
