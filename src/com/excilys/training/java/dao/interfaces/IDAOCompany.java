package com.excilys.training.java.dao.interfaces;

import java.util.ArrayList;

import com.excilys.training.java.modele.Company;

public interface IDAOCompany {

	/**
	 * Gets a company 
	 * @param id the id of the company
	 * @return the company
	 */
	public Company getCompany(int id);

	/**
	 * Gets all companies
	 * @return all companies
	 */
	public ArrayList<Company> getAllCompany();
}
