package com.jdawidowska.service.data.repos;

import com.jdawidowska.service.data.entities.Rented;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentedRepository extends CrudRepository<Rented, Long>{

}