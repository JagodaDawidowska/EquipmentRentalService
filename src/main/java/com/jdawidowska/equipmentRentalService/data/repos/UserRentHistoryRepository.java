package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse;
import com.jdawidowska.equipmentRentalService.data.entities.UserRentHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface UserRentHistoryRepository extends CrudRepository<UserRentHistory, Long> {

    @Modifying
    @Query("UPDATE UserRentHistory " +
            "SET returnDate = :returnDate " +
            "WHERE id = :idHistory")
    void updateReturnDate(Long idHistory, Date returnDate);

    @Transactional
    @Query("SELECT new com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse(U.email, I.itemName, UR.rentDate ,UR.returnDate) " +
            "FROM UserRentHistory AS UR " +
            "JOIN Inventory AS I " +
            "ON UR.idItem = I.id " +
            "JOIN User AS U " +
            "ON UR.idUser = U.id " +
            "WHERE UR.idUser = :idUser ")
    List<UserRentHistoryResponse> findAllHistoryForUser(Long idUser);

    /*@Transactional
    @Modifying
    @Query(value = "SELECT new com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponseUnused(UR.idUser, I.itemName, UR.rentDate ,UR.returnDate) " +
            "FROM UserRentHistory AS UR " +
            "JOIN Inventory AS I " +
            "ON UR.idItem = I.id " +
            "WHERE UR.idUser = :idUser ")
    List<UserRentHistoryResponseUnused> findHistoryForUser(Long idUser);*/
}
