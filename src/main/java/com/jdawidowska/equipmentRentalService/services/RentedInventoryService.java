package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentedResponse;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.repos.InventoryRepository;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentedInventoryService {

    private final RentedInventoryRepository rentedInventoryRepository;
    private final InventoryRepository inventoryRepository;


    public RentedInventoryService(RentedInventoryRepository rentedInventoryRepository, InventoryRepository inventoryRepository) {
        this.rentedInventoryRepository = rentedInventoryRepository;

        this.inventoryRepository = inventoryRepository;
    }

//    public boolean findById(RentingRequest rentingRequest) {
//
//        RentedInventory rentedInventory = rentedInventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
//        if (rentedInventory  == null) {
//            return false;
//        } else {
//            rentedInventoryRepository.findById(rentingRequest.getIdUser());
//            return true;
//        }
//    }

    public List<UserRentedResponse> findUserRentedEquipment(Long idUser){
       return rentedInventoryRepository.findUserRentedEquipment(idUser);
    }

    public List<RentedInventory> getAll(){
        return (List<RentedInventory>) rentedInventoryRepository.findAll();
    }

    //public
}
