package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.RentedInventoryResponse;
import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentedItemResponse;
import com.jdawidowska.equipmentRentalService.data.repos.RentedInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentedInventoryService {

    private final RentedInventoryRepository rentedInventoryRepository;

    public RentedInventoryService(RentedInventoryRepository rentedInventoryRepository) {
        this.rentedInventoryRepository = rentedInventoryRepository;
    }

    public List<RentedInventoryResponse> findAllRentedInventory() {
        return rentedInventoryRepository.findAllRentedInventory();
    }

    public List<UserRentedItemResponse> findEquipmentRentedByUser(Long idUser) {
        return rentedInventoryRepository.findEquipmentRentedByUser(idUser);
    }

    /*
    public List<RentedInventory> findAll(){
        return (List<RentedInventory>) rentedInventoryRepository.findAll();
    }
    */
}
