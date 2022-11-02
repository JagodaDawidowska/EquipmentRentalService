package com.jdawidowska.equipmentRentalService.api.dto.response;

public class FeedbackResponse {

    private final String email;
    private final String content;

    public FeedbackResponse(String email, String content) {
        this.email = email;
        this.content = content;
    }
}
