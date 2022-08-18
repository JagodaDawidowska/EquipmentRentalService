package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.data.repos.FeedbackRepository;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }
}
