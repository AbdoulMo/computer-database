package com.excilys.cdb.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.dao.JDBCCompany;
import com.excilys.cdb.exceptions.DataNotFoundException;
import com.excilys.cdb.model.Company;

public class CompanyServices {

	private ApplicationContext applicationContext;
	private static JDBCCompany daoCompany;
	
	public CompanyServices() {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		daoCompany = (JDBCCompany) applicationContext.getBean(JDBCCompany.class);
	}

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
