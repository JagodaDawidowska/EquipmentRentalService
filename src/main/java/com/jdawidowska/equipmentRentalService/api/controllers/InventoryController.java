package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.GeneralResponse;
import com.jdawidowska.equipmentRentalService.api.dto.response.RentingEnum;
import com.jdawidowska.equipmentRentalService.api.dto.response.RentingResponse;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.services.InventoryService;
import com.jdawidowska.equipmentRentalService.services.RentingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final RentingService rentingService;

    public InventoryController(InventoryService inventoryService, RentingService rentingService) {
        this.inventoryService = inventoryService;
        this.rentingService = rentingService;
    }

    @GetMapping("/showResources")
    public Iterable<Inventory> findAll() {
        return inventoryService.findAll();
    }


}
