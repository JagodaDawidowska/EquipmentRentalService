package com.jdawidowska.equipmentRentalService.api.dto.response;

public class FeedbackResponseUnused {

    private Long idUser;
    private String content;

    public FeedbackResponseUnused(Long idUser, String content) {
        this.idUser = idUser;
        this.content = content;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public FeedbackResponseUnused() {
    }

    @Override
    public String toString() {
        return "FeedbackResponse{" +
                "idUser=" + idUser +
                ", content='" + content + '\'' +
                '}';
    }
}
