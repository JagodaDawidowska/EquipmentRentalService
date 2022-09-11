package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.api.dto.response.RentedInventoryResponse;
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

    @Transactional
    @Modifying
    @Query(value = " SELECT new com.jdawidowska.equipmentRentalService.api.dto.response.RentedInventoryResponse " +
                    "(U.name, U.surname, I.itemName, R.amount) "+
                    "FROM RentedInventory AS R " +
                    "JOIN User AS U " +
                    "ON R.idUser = U.id " +
                    "JOIN Inventory AS I " +
                    "ON R.idItem = I.id")
    public List<RentedInventoryResponse> findRentedInventoryDTO();

    /*
    SELECT RENTED_INVENTORY.amount, USER.name, USER.surname, INVENTORY.item_name FROM RENTED_INVENTORY INNER JOIN USER ON RENTED_INVENTORY.ID_USER = USER.ID INNER JOIN INVENTORY ON RENTED_INVENTORY.ID_ITEM = INVENTORY.ID
     */
    /*
    SELECT USER.name, USER.surname, INVENTORY.item_name, RENTED_INVENTORY.amount FROM RENTED_INVENTORY INNER JOIN USER ON RENTED_INVENTORY.ID_USER = USER.ID INNER JOIN INVENTORY ON RENTED_INVENTORY.ID_ITEM = INVENTORY.ID
     */
    //https://www.youtube.com/watch?v=IG6PISdaGyo
}