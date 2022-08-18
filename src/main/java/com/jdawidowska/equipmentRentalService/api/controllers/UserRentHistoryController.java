package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.services.UserRentHistoryService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRentHistoryController {
    private final UserRentHistoryService userRentHistoryService;

    public UserRentHistoryController(UserRentHistoryService userRentHistoryService) {
        this.userRentHistoryService = userRentHistoryService;
    }
}
