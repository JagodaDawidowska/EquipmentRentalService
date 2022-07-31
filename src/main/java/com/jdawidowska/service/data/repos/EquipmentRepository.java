package com.jdawidowska.service.data.repos;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EquipmentRepository /*extends CrudRepository<Equipment, Long>*/{

	//zeby uzywac UPDATE / DELETE musza byc adnotacje transactional i modyfing
	@Transactional
	@Modifying
	@Query(value="UPDATE Equipment "
			+ "SET available_Amount = available_Amount - 1 "
			+ "WHERE id = :id "
			+ "AND available_Amount > 0")
	public void lendEquipment(Long id);

	@Transactional
	@Modifying
	@Query(value="UPDATE Equipment "
			+ "SET available_Amount = available_Amount + 1 "
			+ "WHERE id = :id "
			+ "AND available_Amount >= 0")
	public void returnEquipment(Long id);

}