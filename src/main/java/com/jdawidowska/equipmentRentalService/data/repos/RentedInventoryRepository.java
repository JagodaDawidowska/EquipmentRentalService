package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedInventoryRepository extends CrudRepository<RentedInventory, Long>{

    @Query(value = "SELECT id FROM Rented_Inventory WHERE id_User =:idUser AND id_Item =:idItem", nativeQuery = true)
    public Long getDefinedId(Long idUser, Long idItem);
}