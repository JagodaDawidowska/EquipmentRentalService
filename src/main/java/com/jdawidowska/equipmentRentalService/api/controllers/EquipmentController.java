package com.jdawidowska.equipmentRentalService.api.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController("/equipment")
public class EquipmentController {

  /* private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }
  */
    /*
    @GetMapping("/findAllEquipment")
    public Iterable<Equipment> findAllEquipment() {
        return equipmentService.findAllEquipment();
    }
    */
/*
    @GetMapping("/lend/{id}")
    public String lendEquipment(@PathVariable Long id) {
        if (!equipmentService.lendEquipment(id)) {
            return "Equipment currently unavailable";
        }
        return "Success";
    }

    @GetMapping("/return/{id}")
    public String returnEquipment(@PathVariable Long id) {
        if (equipmentService.returnEquipment(id)) {
            return "Success";
        }
        return "Inventory is full";
    }
    */
}