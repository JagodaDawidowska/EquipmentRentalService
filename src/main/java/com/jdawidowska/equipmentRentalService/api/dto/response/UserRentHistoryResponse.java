package com.jdawidowska.equipmentRentalService.api.dto.response;

import java.util.Date;

public class UserRentHistoryResponse {
    private final String itemName;
    private final Date rentDate;
    private final Date returnDate;
    private final String email;

    public UserRentHistoryResponse(String email, String itemName, Date rentDate, Date returnDate) {
        this.itemName = itemName;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.email = email;
    }

    public String getItemName() {
        return itemName;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public String getEmail() {
        return email;
    }
}
