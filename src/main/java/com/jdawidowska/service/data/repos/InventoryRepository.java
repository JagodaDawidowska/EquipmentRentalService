package com.jdawidowska.service.data.repos;

import com.jdawidowska.service.data.entities.Inventory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long>{

}