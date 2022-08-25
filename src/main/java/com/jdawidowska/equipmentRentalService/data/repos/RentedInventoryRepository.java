package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.data.entities.RentedInventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentedInventoryRepository extends CrudRepository<RentedInventory, Long>{

    @Query(value = "SELECT id FROM Rented_Inventory WHERE id_User =:idUser AND id_Item =:idItem", nativeQuery = true)
    public Long getDefinedId(Long idUser, Long idItem);

    @Query(value = "SELECT id FROM Rented_Inventory WHERE id_User =:idUser AND id_Item =:idItem", nativeQuery = true)
    public List<Long>  getDefinedIds(Long idUser, Long idItem);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Rented_Inventory "
            + " SET amount = amount + 1 "
            +"WHERE id_User = :idUser AND id_Item = :idItem "
             ,nativeQuery = true)
    public void rentedInventoryRent(Long idUser, Long idItem);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Rented_Inventory  " +
            "SET amount = amount -1 " +
            " WHERE id_User =:idUser AND id_Item =:idItem "
              ,nativeQuery = true)
    public void returnInventoryReturn(Long idUser, Long idItem);

    Optional<RentedInventory>  findByIdUserAndIdItem(Long idUser, Long idItem);
}