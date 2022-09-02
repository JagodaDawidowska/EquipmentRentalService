package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentedResponse;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentedInventoryRepository extends CrudRepository<RentedInventory, Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT new com.jdawidowska.equipmentRentalService.api.dto.response.UserRentedResponse(R.id, I.itemName, R.amount) " +
            "FROM RentedInventory AS R " +
            "JOIN Inventory AS I " +
            "ON R.idItem = I.id " +
            "WHERE R.idUser = :idUser ")
    public List<UserRentedResponse> findEquipmentRentedByUser(Long idUser);
}