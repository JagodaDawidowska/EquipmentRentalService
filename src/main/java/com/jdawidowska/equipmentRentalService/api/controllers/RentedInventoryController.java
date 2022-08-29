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
    public List<RentedInventory> getAll(){
        return rentedInventoryService.getAll();
    }

    @GetMapping("/user/{IdUser}")
    public List<UserRentedResponse> findUserRentedEquipment(@PathVariable("IdUser") Long idUser){
        return rentedInventoryService.findUserRentedEquipment(idUser);
    }
}
