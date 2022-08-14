package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedInventoryRepository extends CrudRepository<RentedInventory, Long>{


    //Select * from Rented_Inventory where id_customer='99'

    //getting id RentedInventory by id customer and id user
    @Query(value = "SELECT id FROM Rented_Inventory WHERE id_Customer =:id_Customer AND id_Item =:id_Item", nativeQuery = true)
    public Long getDefinedId(Long id_Customer, Long id_Item);
}