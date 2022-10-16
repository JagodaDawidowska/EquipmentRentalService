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

//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE RentedInventory "
//            + "SET amount = amount + 1 "
//            + "WHERE idItem = :idItem AND idUser = :IdUser")
//    public Integer doIncrementAmountWhereSameIdItemForIdUser(Long idItem,Long IdUser);
//
//    @Transactional
//    @Modifying
//    @Query(value = "select amount from RentedInventory where idUser = :idUser")
//    public Integer getAmountByIdUser(Long idUser);
//
//    @Transactional
//    @Modifying
//    @Query(value = "select idItem from RentedInventory where idUser = :idUser")
//    public Integer getIdItemByIdUser(Long idUser);


    @Transactional
    @Modifying
    @Query(value = "UPDATE RentedInventory "
            + "SET amount = amount + 1 "
            + "WHERE id = :id "
            + "and amount >= 0")
    public void doIncrementAmount(Long id);

    @Query
    public boolean existsByIdUserAndIdItem(Long idUser, Long idItem);

    @Transactional
    @Query(value = "SELECT id FROM RentedInventory where idUser =:idUser  and idItem=:idItem")
    public Long getIdRentedInventoryByIdUserAndIdItem(Long idUser,Long idItem);

    @Transactional
    @Modifying
    @Query(value = "update  RentedInventory set amount =amount-1  where id =:idRentedInventory ")
    public void doDecreaseAmount(Long idRentedInventory);




    /*ByIdUserAndIdItem()
    SELECT RENTED_INVENTORY.amount, USER.name, USER.surname, INVENTORY.item_name FROM RENTED_INVENTORY INNER JOIN USER ON RENTED_INVENTORY.ID_USER = USER.ID INNER JOIN INVENTORY ON RENTED_INVENTORY.ID_ITEM = INVENTORY.ID
     */
    /*
    SELECT USER.name, USER.surname, INVENTORY.item_name, RENTED_INVENTORY.amount FROM RENTED_INVENTORY INNER JOIN USER ON RENTED_INVENTORY.ID_USER = USER.ID INNER JOIN INVENTORY ON RENTED_INVENTORY.ID_ITEM = INVENTORY.ID
     */
    //https://www.youtube.com/watch?v=IG6PISdaGyo
}