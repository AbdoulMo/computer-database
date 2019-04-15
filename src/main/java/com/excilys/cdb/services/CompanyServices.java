package com.excilys.cdb.services;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.cdb.dao.JDBCCompany;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;

public class CompanyServices {

	private static JDBCCompany daoCompany = new JDBCCompany();

	public Company getCompanyByID(int id) throws Exception {
		Optional<Company> company = daoCompany.getCompanyByID(id);
		if (company.isPresent()) {
			return company.get();
		} else {
			throw new DataNotFoundException();
		}
	}

	public ArrayList<Company> getAllCompany() throws DataNotFoundException {
		ArrayList<Company> companyList = daoCompany.getAllCompany();
		if (companyList.isEmpty()) {
			throw new DataNotFoundException();
		}
		return daoCompany.getAllCompany();
	}
	
	public boolean deleteCompany(int id) {
		return daoCompany.deleteCompany(id);
	}
}
