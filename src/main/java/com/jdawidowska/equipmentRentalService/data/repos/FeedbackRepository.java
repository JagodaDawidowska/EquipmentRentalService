package com.jdawidowska.equipmentRentalService.data.repos;

import com.jdawidowska.equipmentRentalService.api.dto.response.FeedbackResponseDTO;
import com.jdawidowska.equipmentRentalService.data.entities.Feedback;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback,Long> {

    @Transactional
    @Modifying
    @Query(value = " SELECT new com.jdawidowska.equipmentRentalService.api.dto.response.FeedbackResponseDTO " +
            "(U.email, F.content) "+
            "FROM Feedback AS F " +
            "JOIN User AS U " +
            "ON F.idUser = U.id ")
    public List<FeedbackResponseDTO> findFeedbackResponseDTO();
}