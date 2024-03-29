package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.response.FeedbackResponse;
import com.jdawidowska.equipmentRentalService.services.FeedbackService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    //For Admin FeedbackActivity
    @GetMapping()
    public List<FeedbackResponse> findAllFeedback() {
        return feedbackService.findAllFeedback();
    }

    /*
    @GetMapping
    public Iterable<Feedback> findAll() {
        return feedbackService.findAll();
    }

    //https://www.javatpoint.com/dto-java
    @GetMapping("/getAllfeedbackDTO")
    public List<FeedbackResponse> getAllFeedbacksDTO() {
        // call getAllUsersLocation() method from the service which we created before
        // store the result in a list of UserLocationDTO
        List <FeedbackResponse> feedbackResponses = feedbackService.getAllUsersFeedbackResponse();
        // return usersLocation
        return feedbackResponses;
    }
    */
}
