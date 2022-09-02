package com.jdawidowska.equipmentRentalService.api.dto.response;

public class FeedbackResponse {

    private Long idUser;
    private String content;

    public FeedbackResponse(Long idUser, String content) {
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

    public FeedbackResponse() {
    }

    @Override
    public String toString() {
        return "FeedbackResponse{" +
                "idUser=" + idUser +
                ", content='" + content + '\'' +
                '}';
    }
}
