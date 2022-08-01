package com.equipmentRentalService.service.data.repos;

import com.equipmentRentalService.service.data.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Long>{

}