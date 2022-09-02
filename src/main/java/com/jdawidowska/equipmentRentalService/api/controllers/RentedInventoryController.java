package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentedResponse;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.services.RentedInventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/rentedInventory")
public class RentedInventoryController {

    private final RentedInventoryService rentedInventoryService;

    public RentedInventoryController(RentedInventoryService rentedInventoryService) {
        this.rentedInventoryService = rentedInventoryService;
    }

    @GetMapping()
    public List<RentedInventory> findAll(){
        return rentedInventoryService.findAll();
    }

    @GetMapping("/user/{idUser}")
    public List<UserRentedResponse> findEquipmentRentedByUser(@PathVariable("idUser") Long idUser){
        return rentedInventoryService.findEquipmentRentedByUser(idUser);
    }
}
