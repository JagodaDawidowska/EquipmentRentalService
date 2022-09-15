package com.jdawidowska.equipmentRentalService.services;

import com.jdawidowska.equipmentRentalService.api.dto.response.RentedInventoryResponse;
import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentedResponse;
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

    public List<RentedInventory> findAll(){
        return (List<RentedInventory>) rentedInventoryRepository.findAll();
    }

    public List<UserRentedResponse> findEquipmentRentedByUser(Long idUser){
       return rentedInventoryRepository.findEquipmentRentedByUser(idUser);
    }

    public List<RentedInventoryResponse> findRentedInventoryDTO(){
        return rentedInventoryRepository.findRentedInventoryDTO();
    }
}
