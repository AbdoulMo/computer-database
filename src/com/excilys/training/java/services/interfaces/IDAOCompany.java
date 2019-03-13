package com.excilys.training.java.services.interfaces;

import java.util.List;

import com.excilys.training.java.modele.Company;

public interface IDAOCompany {

	public Company getCompany(int id);

	public List<Company> getAllCompany();
}
