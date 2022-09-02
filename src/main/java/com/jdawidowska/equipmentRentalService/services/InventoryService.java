package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.InventoryRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Iterable<Inventory> findAll() {
        return inventoryRepository.findAll();
    }

    public boolean add(InventoryRequest inventoryRequest) {
      Inventory inventory = new Inventory();
      inventory.setItemName(inventoryRequest.getItemName());
      inventory.setTotalAmount(inventoryRequest.getTotalAmount());
      inventory.setAvailableAmount(inventoryRequest.getAvailableAmount());
      inventoryRepository.save(inventory);
      return true;
    }

    public boolean remove(InventoryRequest inventoryRequest){
        Inventory inventory = inventoryRepository.findById(inventoryRequest.getIdItem()).orElse(null);
        if(inventory != null) {
            inventoryRepository.deleteById(inventory.getId());
            return true;
        } else return false;
    }
}

