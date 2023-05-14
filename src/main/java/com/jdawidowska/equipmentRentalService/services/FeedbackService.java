package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.FeedbackResponse;
import com.jdawidowska.equipmentRentalService.data.repos.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    //For Admin FeedbackActivity
    public List<FeedbackResponse> findAllFeedback() {
        return feedbackRepository.findAllFeedback();
    }

    /*
    public Iterable<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    public List<FeedbackResponseUnused> getAllUsersFeedbackResponse() {
        return ((List<Feedback>) feedbackRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    private FeedbackResponseUnused convertDataIntoDTO (Feedback feedback) {

        // create instance of our UserLocationDTO class
        FeedbackResponseUnused feedbackResponseUnused = new FeedbackResponseUnused();

        //set username and userId in dto from the userData
        feedbackResponseUnused.setIdUser(feedback.getIdUser());
        feedbackResponseUnused.setContent(feedback.getContent());

        return feedbackResponseUnused;
    }
    */
}
