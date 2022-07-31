package com.jdawidowska.service.data.repos;

import com.jdawidowska.service.data.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends CrudRepository<Customer, Long>{

}