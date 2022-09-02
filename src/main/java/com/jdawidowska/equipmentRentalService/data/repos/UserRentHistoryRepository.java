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
    public void updateReturnDate(Long idHistory, Date returnDate);

    @Transactional
    @Modifying
    @Query(value = "SELECT new com.jdawidowska.equipmentRentalService.api.dto.response.UserRentHistoryResponse(UR.idUser, I.itemName, UR.rentDate ,UR.returnDate) " +
            "FROM UserRentHistory AS UR " +
            "JOIN Inventory AS I " +
            "ON UR.idItem = I.id " +
            "WHERE UR.idUser = :idUser ")
    public List<UserRentHistoryResponse> findHistoryForUser(Long idUser);
}
