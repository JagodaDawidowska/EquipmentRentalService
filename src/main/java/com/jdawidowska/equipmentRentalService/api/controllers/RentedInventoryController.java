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


    @GetMapping
    public List<RentedInventory> getAll(){
        return rentedInventoryService.getAll();
    }
}
