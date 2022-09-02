package com.jdawidowska.equipmentRentalService.api.dto.request;

public class ReturnRequest {
    private long idUser;
    private long idItem;
    private String feedback;

    public ReturnRequest(long idUser, long idItem, String feedback) {
        this.idUser = idUser;
        this.idItem = idItem;
        this.feedback = feedback;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdItem() {
        return idItem;
    }

    public void setIdItem(long idItem) {
        this.idItem = idItem;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}