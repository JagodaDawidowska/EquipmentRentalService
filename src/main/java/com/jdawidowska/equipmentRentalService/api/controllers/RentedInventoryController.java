package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.GeneralEnum;
import com.jdawidowska.equipmentRentalService.api.dto.response.GeneralResponse;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.services.RentedInventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/rentedInventory")
public class RentedInventoryController {

    private final RentedInventoryService rentedInventoryService;

    public RentedInventoryController(RentedInventoryService rentedInventoryService) {
        this.rentedInventoryService = rentedInventoryService;
    }

    @PostMapping("/getAllRentedInventoryById")
    public ResponseEntity<GeneralResponse> getAllRentedItemsByIDUser(@RequestBody RentingRequest rentingRequest) {
        if (rentedInventoryService.findById(rentingRequest)) {
            GeneralResponse correctResponse = new GeneralResponse(GeneralEnum.SUCCESS);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            GeneralResponse incorrectResponse = new GeneralResponse(GeneralEnum.FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<RentedInventory> getAll(){
        return rentedInventoryService.getAll();
    }
}
