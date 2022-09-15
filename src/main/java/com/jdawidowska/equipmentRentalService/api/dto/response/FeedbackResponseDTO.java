package com.jdawidowska.equipmentRentalService.api.dto.response;

public class FeedbackResponseDTO {
    String email;
    String content;

    public FeedbackResponseDTO(String email, String content) {
        this.email = email;
        this.content = content;
    }

    public FeedbackResponseDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FeedbackResponseDTO{" +
                "email='" + email + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
