package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Inventory " +
            "SET availableAmount = availableAmount - 1 " +
            "WHERE id = :id " +
            "AND availableAmount > 0")
    void rentItem(Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Inventory " +
            "SET availableAmount = availableAmount + 1 " +
            "WHERE id = :id " +
            "AND availableAmount < totalAmount")
    void returnItem(Long id);
}