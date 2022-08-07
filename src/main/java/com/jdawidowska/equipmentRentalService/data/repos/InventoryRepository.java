package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long>{
    @Transactional
    @Modifying
    @Query(value="UPDATE Inventory "
            + "SET available_Amount = available_Amount - 1 "
            + "WHERE id = :id "
            + "AND available_Amount > 0")
    public void lendItem(Long id);


    //potrzebuje id do tego
//    @Query(value = "SELECT available_Amount from Inventory")
//    public Integer getByAvailableAmount(Long id);
//    //availableAmount
//    @Override
//    Optional<Inventory> findById(Long aLong);
}