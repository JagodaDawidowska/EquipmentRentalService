package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.request.RentingRequest;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentedInventoryService {

    private final RentedInventoryRepository rentedInventoryRepository;

    public RentedInventoryService(RentedInventoryRepository rentedInventoryRepository) {
        this.rentedInventoryRepository = rentedInventoryRepository;
    }

    public boolean findById(RentingRequest rentingRequest) {

        RentedInventory rentedInventory = rentedInventoryRepository.findById(rentingRequest.getIdItem()).orElse(null);
        if (rentedInventory  == null) {
            return false;
        } else {
            rentedInventoryRepository.findById(rentingRequest.getIdUser());
            return true;
        }
    }

    public List<RentedInventory> getAll(){
        return (List<RentedInventory>) rentedInventoryRepository.findAll();
    }
}
