package com.excilys.training.java.dao.interfaces;

import java.util.ArrayList;

import com.excilys.training.java.modele.Company;

public interface IDAOCompany {

	public Company getCompany(int id);

	public ArrayList<Company> getAllCompany();
}
