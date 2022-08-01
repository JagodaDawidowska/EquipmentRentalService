package com.equipmentRentalService.service.data.repos;

import com.equipmentRentalService.service.data.entities.Rented_Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedRepository extends CrudRepository<Rented_Inventory, Long>{

}