package com.excilys.cdb.persistence.dao;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.excilys.cdb.model.Company;

public interface IDAOCompany extends CrudRepository<Company, Integer>{
		
	ArrayList<Company> findAll();
}
