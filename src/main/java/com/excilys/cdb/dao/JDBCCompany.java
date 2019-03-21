package com.excilys.cdb.dao;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.CompanyMapper;

public class JDBCCompany {

	private DatabaseParam databaseParam = DatabaseParam.getInstance();
	private final static Logger logger = Logger.getLogger(JDBCCompany.class);
	private static final String QUERY_GET_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPANY = "SELECT id, name FROM company";
	private ResultSet resultSet;

	public Optional<Company> getCompanyByID(int id) {
		Company company = null;
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_COMPANY_BY_ID);) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			company = CompanyMapper.resultSetToCompany(resultSet);
		} catch (SQLException e) {
			logger.error("Error while trying to get company with specified ID !", e);
		}
		return Optional.ofNullable(company);
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> lCompany = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_ALL_COMPANY);) {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company company = CompanyMapper.resultSetToCompany(resultSet);
				lCompany.add(company);
			}
		} catch (SQLException e) {
			logger.error("Error while trying to get all company !", e);
		}
		return lCompany;
	}
}
