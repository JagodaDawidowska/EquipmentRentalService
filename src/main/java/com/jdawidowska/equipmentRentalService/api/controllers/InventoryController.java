package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.InventoryRequest;
import com.jdawidowska.equipmentRentalService.api.dto.response.*;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Iterable<Inventory> findAll() {
        return inventoryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<InventoryResponse> add(@RequestBody InventoryRequest inventoryRequest) {
        if (inventoryService.add(inventoryRequest)) {
            InventoryResponse correctResponse = new InventoryResponse(InventoryResponseEnum.EQUIPMENT_ADDED);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            InventoryResponse incorrectResponse = new InventoryResponse(InventoryResponseEnum.FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<InventoryResponse> remove(@RequestBody InventoryRequest inventoryRequest) {
        if (inventoryService.remove(inventoryRequest)) {
            InventoryResponse correctResponse = new InventoryResponse(InventoryResponseEnum.EQUIPMENT_REMOVED);
            return new ResponseEntity<>(correctResponse, HttpStatus.OK);
        } else {
            InventoryResponse incorrectResponse = new InventoryResponse(InventoryResponseEnum.FAIL);
            return new ResponseEntity<>(incorrectResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
