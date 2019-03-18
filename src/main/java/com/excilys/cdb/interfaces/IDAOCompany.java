package com.excilys.cdb.interfaces;

import java.util.List;

import com.excilys.cdb.modele.Company;

public interface IDAOCompany {

	public Company getCompany(int id);

	public List<Company> getAllCompany();
}
