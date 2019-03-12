package com.excilys.training.java.services.interfaces;

import java.util.List;

import com.excilys.training.java.modele.Company;

public interface IDAOCompany {

	public Company getCompany(String name);

	public List<Company> getAllCompany();
	
	public boolean addCompany(Company c);
	
	public boolean deleteCompany(Company c);
	
	public boolean updateCompany(Company c);
}
