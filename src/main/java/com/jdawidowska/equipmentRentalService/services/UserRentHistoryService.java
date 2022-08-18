package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.data.repos.UserRentHistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRentHistoryService {
    private final UserRentHistoryRepository userRentHistoryRepository;

    public UserRentHistoryService(UserRentHistoryRepository userRentHistoryRepository) {
        this.userRentHistoryRepository = userRentHistoryRepository;
    }
}
