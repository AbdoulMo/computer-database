package com.excilys.training.java.services.interfaces;

import java.util.List;

import com.excilys.training.java.modele.Company;

public interface IDAOCompany {

	public Company getCompany(String name);

	public List<Company> getAllCompany();
	
	public int addCompany(Company c);
	
	public int deleteCompany(Company c);
	
	public int updateCompany(Company c);
}
