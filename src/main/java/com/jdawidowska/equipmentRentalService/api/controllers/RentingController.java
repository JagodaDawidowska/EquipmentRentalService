package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentRequest;
import com.jdawidowska.equipmentRentalService.api.dto.request.ReturnRequest;
import com.jdawidowska.equipmentRentalService.exception.ItemNotFoundException;
import com.jdawidowska.equipmentRentalService.exception.RentHistoryNotFoundException;
import com.jdawidowska.equipmentRentalService.services.RentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/renting")
public class RentingController {

    private final RentingService rentingService;

    public RentingController(RentingService rentingService) {
        this.rentingService = rentingService;
    }

    @PostMapping
    @RequestMapping("/rent")
    public ResponseEntity<Void> rentEquipment(@RequestBody @Valid RentRequest rentRequest) {
        try {
            rentingService.rentItem(rentRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    @RequestMapping("/return")
    public ResponseEntity<Void> returnEquipment(@RequestBody @Valid ReturnRequest returnRequest){
        try {
            rentingService.returnItem(returnRequest);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RentHistoryNotFoundException | ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
