package com.excilys.cdb.dao;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.cdb.model.Company;

public class JDBCCompany {

	private final static Logger logger = Logger.getLogger(JDBCCompany.class);
	private static final String QUERY_GET_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPANY = "SELECT id, name FROM company";
	private static final String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private static final String QUERY_DELETE_COMPUTER_WITH_COMPANY_ID = "DELETE FROM computer WHERE company_id = ?";
	private JdbcTemplate jdbcTemplate;

	public JDBCCompany(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Optional<Company> getCompanyByID(int id) {

		Company company = null;
		try {
			company = jdbcTemplate.queryForObject(QUERY_GET_COMPANY_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<Company>(Company.class));
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return Optional.ofNullable(company);
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> companyList = new ArrayList<>();
		try {
			companyList = (ArrayList<Company>) jdbcTemplate.query(QUERY_GET_ALL_COMPANY,
					new BeanPropertyRowMapper<Company>(Company.class));
		} catch (DataAccessException e) {
			logger.error("Cannot get company List", e);
		}
		return companyList;
	}

	public boolean deleteCompany(int id) {
		int resultQuery1 = 0;
		try {
			resultQuery1 = jdbcTemplate.update(QUERY_DELETE_COMPUTER_WITH_COMPANY_ID, id);
		} catch (DataAccessException e1) {
			logger.error("Unable to delete all computer from the company", e1);
		}
		int resultQuery2 = 0;
		try {
			resultQuery2 = jdbcTemplate.update(QUERY_DELETE_COMPANY, id);
		} catch (DataAccessException e1) {
			logger.error("Unable to delete the company");
		}
		return (resultQuery1 > 0 && resultQuery2 > 0) ? true : false;
	}
}
