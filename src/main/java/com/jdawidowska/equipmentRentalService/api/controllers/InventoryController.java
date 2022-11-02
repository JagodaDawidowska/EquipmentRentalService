package com.jdawidowska.equipmentRentalService.api.controllers;

import com.jdawidowska.equipmentRentalService.api.dto.request.AddInventoryRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.exception.ItemNotFoundException;
import com.jdawidowska.equipmentRentalService.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<Void> addItem(@RequestBody @Valid AddInventoryRequest addInventoryRequest) {
        inventoryService.addItem(addInventoryRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/remove/{idItem}")
    public ResponseEntity<Void> removeItem(@PathVariable Long idItem) {
        try {
            inventoryService.removeItem(idItem);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
