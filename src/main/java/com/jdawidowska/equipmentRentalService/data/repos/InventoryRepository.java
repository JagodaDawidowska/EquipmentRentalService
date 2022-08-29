package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import com.jdawidowska.equipmentRentalService.model.EquipmentEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long>{
    @Transactional
    @Modifying
    @Query(value="UPDATE Inventory "
            + "SET available_Amount = available_Amount - 1 "
            + "WHERE id = :id "
            + "AND available_Amount > 0")
    public void rentItem(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Inventory "
    + "SET available_Amount = available_Amount + 1 "
    + "WHERE id = :id "
    + "and available_Amount >= 0")
    public void returnItem(Long id);

    /*@Query(value = "")
    public String getName();*/
}