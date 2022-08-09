package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.repos.CustomersRepository;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class RentingService {

    private final InventoryRepository inventoryRepository;
    private final CustomersRepository customersRepository;
    public final RentedInventoryRepository rentedInventoryRepository;

    public RentingService(InventoryRepository inventoryRepository, CustomersRepository customersRepository, RentedInventoryRepository rentedInventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.customersRepository = customersRepository;
        this.rentedInventoryRepository = rentedInventoryRepository;
    }

    public boolean rent(RentingRequest rentingRequest) {

        Inventory inventory = inventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }
        if (inventory.getAvailableAmount() > 0) {
            RentedInventory rentedInventory = new RentedInventory();
            rentedInventory.setIdCustomer(rentingRequest.getIdCustomer());
            rentedInventory.setIdItem(rentingRequest.getIdItem());
            rentedInventory.setAmount(1);
            rentedInventoryRepository.save(rentedInventory);
            inventoryRepository.lendItem(rentingRequest.getIdItem());
            return true;
        } else {
            return false;
        }
    }

    public boolean returnItem(RentingRequest rentingRequest) {
        Inventory inventory = inventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (inventory == null) {
            return false;
        }

        RentedInventory rentedInventory = new RentedInventory();

        //rentedInventoryRepository.deleteById(rentedInventory.getId());
        //findById(rentingRequest.getIdItem()).orElse(null);

        if (inventory.getAvailableAmount() < inventory.getTotalAmount()) {
            inventoryRepository.returnItem(rentingRequest.getIdItem());
            //  rentedInventoryRepository.deleteById(rentedInventory.getId());

            //to do // add rentedInventoryRepository.deleteById( Id have to be taken from inventory repository)
            //query for take id customer and item

            return true;
        } else return false;
    }
}
