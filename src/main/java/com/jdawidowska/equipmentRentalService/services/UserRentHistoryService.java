package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponseUnused;
import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse;
import com.jdawidowska.equipmentRentalService.data.entities.UserRentHistory;
import com.jdawidowska.equipmentRentalService.data.repos.UserRentHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRentHistoryService {

    private final UserRentHistoryRepository userRentHistoryRepository;

    public UserRentHistoryService(UserRentHistoryRepository userRentHistoryRepository) {
        this.userRentHistoryRepository = userRentHistoryRepository;
    }

    /*public Iterable<UserRentHistory> findAll(){
        return userRentHistoryRepository.findAll();
    }

    public List<UserRentHistoryResponseUnused> findUserHistory(Long id){
        return userRentHistoryRepository.findHistoryForUser(id);
    }*/

    public List<UserRentHistoryResponse> findAllHistoryForUser(Long idUser){
        return userRentHistoryRepository.findAllHistoryForUser(idUser);
    }
}
