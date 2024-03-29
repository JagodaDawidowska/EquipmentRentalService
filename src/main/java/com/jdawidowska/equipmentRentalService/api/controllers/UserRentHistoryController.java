package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse;
import com.jdawidowska.equipmentRentalService.services.UserRentHistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class UserRentHistoryController {
    private final UserRentHistoryService userRentHistoryService;

    public UserRentHistoryController(UserRentHistoryService userRentHistoryService) {
        this.userRentHistoryService = userRentHistoryService;
    }

    @GetMapping("/user/{idUser}")
    public List<UserRentHistoryResponse> findAllHistoryForUser(@PathVariable("idUser") Long idUser) {
        return userRentHistoryService.findAllHistoryForUser(idUser);
    }

    /*
    @GetMapping()
    public Iterable<UserRentHistory> findAll() {
        return userRentHistoryService.findAll();
    }
    */

    /*
    @GetMapping("/user/{idUser}")
    public List<UserRentHistoryResponse> findUserHistory(@PathVariable("idUser") Long idUser) {
        return userRentHistoryService.findUserHistory(idUser);
    }
    */
}