package com.excilys.cdb.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.DataNotFoundException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dao.IDAOCompany;
import com.excilys.cdb.persistence.dao.IDAOComputer;

@Service
public class CompanyServices {

	private IDAOCompany daoCompany;
	private IDAOComputer daoComputer;

	public CompanyServices(IDAOCompany daoCompany, IDAOComputer daoComputer) {
		this.daoCompany = daoCompany;
		this.daoComputer = daoComputer;
	}

	public ArrayList<Company> getAllCompany() throws DataNotFoundException {
		ArrayList<Company> companyList = daoCompany.findAll();
		return companyList;
	}

	public Company getCompanyByID(int id) throws DataNotFoundException {

		Optional<Company> company = daoCompany.findById(id);
		if (company.isPresent()) {
			return company.get();
		} else {
			throw new DataNotFoundException();
		}
	}

	@Transactional
	public void deleteCompany(int id) throws Exception {
		try {
			daoComputer.deleteByManufacturerId(id);
			daoCompany.deleteById(id);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
