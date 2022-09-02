package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse;
import com.jdawidowska.equipmentRentalService.data.entities.UserRentHistory;
import com.jdawidowska.equipmentRentalService.services.UserRentHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/history")
public class UserRentHistoryController {
    private final UserRentHistoryService userRentHistoryService;

    public UserRentHistoryController(UserRentHistoryService userRentHistoryService) {
        this.userRentHistoryService = userRentHistoryService;
    }

    @GetMapping("/all")
    public Iterable<UserRentHistory> findAll(){
        return userRentHistoryService.findAll();
    }

    @GetMapping("/user/{IdUser}")
    public List<UserRentHistoryResponse> findById(@PathVariable("IdUser") Long idUser){
        return userRentHistoryService.findByUserId(idUser);
    }
}
