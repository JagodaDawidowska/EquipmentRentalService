package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.data.entities.Feedback;
import com.jdawidowska.equipmentRentalService.services.FeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public Iterable<Feedback> findAll() {
        return feedbackService.findAll();
    }
}
