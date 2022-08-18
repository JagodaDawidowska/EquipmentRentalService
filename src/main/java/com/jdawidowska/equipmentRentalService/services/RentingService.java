package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.repos.UserRepository;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class RentingService {

    private final InventoryRepository inventoryRepository;
    private final UserRepository userRepository;
    private final RentedInventoryRepository rentedInventoryRepository;

    public RentingService(InventoryRepository inventoryRepository, UserRepository userRepository, RentedInventoryRepository rentedInventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.userRepository = userRepository;
        this.rentedInventoryRepository = rentedInventoryRepository;
    }

    public boolean rent(RentingRequest rentingRequest) {

        Inventory inventory = inventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }
        if (inventory.getAvailableAmount() > 0) {
            //sprawdz czy nie ma juz takiego rekordu jak jest to wywolaj metode z repo ktofra dodaje amount;
            RentedInventory rentedInventory = new RentedInventory();
            rentedInventory.setIdUser(rentingRequest.getIdUser());
            rentedInventory.setIdItem(rentingRequest.getIdItem());
            rentedInventory.setAmount(1);
            rentedInventoryRepository.save(rentedInventory);
            inventoryRepository.rentItem(rentingRequest.getIdItem());
            return true;
        } else {
            return false;
        }
    }


    public boolean returnItem(RentingRequest rentingRequest) {
        Inventory inventory = inventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }if (inventory.getAvailableAmount() < inventory.getTotalAmount()) {

            inventoryRepository.returnItem(rentingRequest.getIdItem());

            Long definedId = rentedInventoryRepository.getDefinedId(rentingRequest.getIdUser(), rentingRequest.getIdItem());
            rentedInventoryRepository.deleteById(definedId);
            return true;

        } else return false;
    }
}
