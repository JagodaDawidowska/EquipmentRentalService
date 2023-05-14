package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.AddInventoryRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.exception.ItemNotFoundException;
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

    public void addItem(AddInventoryRequest addInventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setItemName(addInventoryRequest.getItemName());
        inventory.setTotalAmount(addInventoryRequest.getTotalAmount());
        inventory.setAvailableAmount(addInventoryRequest.getTotalAmount());
        inventoryRepository.save(inventory);
    }

    public void removeItem(Long idItem) throws ItemNotFoundException {
        Inventory inventory = inventoryRepository.findById(idItem).orElse(null);
        if (inventory != null && inventory.getAvailableAmount().equals(inventory.getTotalAmount())) {
            inventoryRepository.deleteById(inventory.getId());
        } else {
            throw new ItemNotFoundException();
        }
    }
}

