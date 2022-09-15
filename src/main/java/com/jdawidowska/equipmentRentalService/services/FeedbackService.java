package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.FeedbackResponse;
import com.jdawidowska.equipmentRentalService.api.dto.response.FeedbackResponseDTO;
import com.jdawidowska.equipmentRentalService.data.entities.Feedback;
import com.jdawidowska.equipmentRentalService.data.repos.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Iterable<Feedback> findAll(){
        return feedbackRepository.findAll();
    }

    public List<FeedbackResponse> getAllUsersFeedbackResponse() {
        return ((List<Feedback>) feedbackRepository
                .findAll())
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    //For Admin FeedbackActivity
    public List<FeedbackResponseDTO>  findFeedbackResponseDTO(){
        return  feedbackRepository.findFeedbackResponseDTO();
    }

    private FeedbackResponse convertDataIntoDTO (Feedback feedback) {

        // create instance of our UserLocationDTO class
        FeedbackResponse feedbackResponse = new   FeedbackResponse();

        //set username and userId in dto from the userData
        feedbackResponse.setIdUser(feedback.getIdUser());
        feedbackResponse.setContent(feedback.getContent());

        return feedbackResponse;
    }
}
