package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse;
import com.jdawidowska.equipmentRentalService.data.entities.UserRentHistory;
import com.jdawidowska.equipmentRentalService.data.repos.UserRentHistoryRepository;
import org.apache.catalina.UserDatabase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRentHistoryService {
    private final UserRentHistoryRepository userRentHistoryRepository;

    public UserRentHistoryService(UserRentHistoryRepository userRentHistoryRepository) {
        this.userRentHistoryRepository = userRentHistoryRepository;
    }
    public Iterable<UserRentHistory> findAll(){
        return userRentHistoryRepository.findAll();
    }
    public List<UserRentHistoryResponse> findByUserId(Long id){
        return userRentHistoryRepository.findUserRentedHistoryById(id);
    }
}
