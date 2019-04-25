package com.excilys.cdb.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.excilys.cdb.model.Computer;

public interface IDAOComputer extends PagingAndSortingRepository<Computer, Integer> {

	final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE company_id = :manufacturerId";

	ArrayList<Computer> findAll();

	@Query(value=QUERY_DELETE_COMPUTER, nativeQuery = true)
	void deleteByManufacturerId(@Param("manufacturerId") Integer manufacturerId);
}
