package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RenteringRequest;
import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.repos.CustomersRepository;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class RenteringService {

    public final InventoryRepository inventoryRepository;
    public final CustomersRepository customersRepository;
    public final RentedInventoryRepository rentedInventoryRepository;



    public RenteringService(InventoryRepository inventoryRepository, CustomersRepository customersRepository, RentedInventoryRepository rentedInventoryRepository) {
        this.inventoryRepository = inventoryRepository;
        this.customersRepository = customersRepository;
        this.rentedInventoryRepository = rentedInventoryRepository;
    }




    public boolean rent(RenteringRequest renteringRequest, Long idItem){
        //czy tworzyc do tego specjalne query wyciagajace amount?
        //  Integer availableAmount = inventoryRepository.getByAvailableAmount(idItem);
        //Equipment eq = equipmentRepo.findById(id).orElse(null);
        Inventory inventory = inventoryRepository.findById(idItem).orElse(null);
        Integer availableAmount = inventory.getAvailableAmount();

       if(availableAmount>=0){
           RentedInventory rentedInventory = new RentedInventory();
           rentedInventory.setIdCustomer(renteringRequest.getIdCustomer());
           rentedInventory.setIdItem(renteringRequest.getIdItem());
           rentedInventory.setAmount(1);
           rentedInventoryRepository.save(rentedInventory);
           inventoryRepository.lendItem(idItem);
           return true;

       }else{
           return false;
       }

    }
}
