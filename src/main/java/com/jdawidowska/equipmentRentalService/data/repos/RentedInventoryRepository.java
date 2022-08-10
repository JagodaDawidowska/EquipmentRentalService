package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentedInventoryRepository extends CrudRepository<RentedInventory, Long>{

    //Select * from Rented_Inventory where id_customer='99'
    @Query(value = "SELECT * FROM Rented_Inventory", nativeQuery = true)
    public List<RentedInventory> findAllByUserIdAndItemId();
}