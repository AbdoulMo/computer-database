package com.excilys.cdb.dao;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.excilys.cdb.hikaricp.HikariCP;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.MapperCompany;

public class JDBCCompany {

	private final static Logger logger = Logger.getLogger(JDBCCompany.class);
	private static final String QUERY_GET_COMPANY_BY_ID = "SELECT id, name FROM company WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPANY = "SELECT id, name FROM company";
	private static final String QUERY_DELETE_COMPANY = "DELETE FROM company WHERE id = ?";
	private static final String QUERY_DELETE_COMPUTER_WITH_COMPANY_ID = "DELETE FROM computer WHERE company_id = ?";
	private ResultSet resultSet;

	public Optional<Company> getCompanyByID(int id) {
		Company company = null;
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_COMPANY_BY_ID);) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			company = MapperCompany.resultSetToCompany(resultSet);
		} catch (SQLException e) {
			logger.error("Error while trying to get company with specified ID !", e);
		}
		return Optional.ofNullable(company);
	}

	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> lCompany = new ArrayList<>();
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_ALL_COMPANY);) {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company company = MapperCompany.resultSetToCompany(resultSet);
				lCompany.add(company);
			}
		} catch (SQLException e) {
			logger.error("Error while trying to get all company !", e);
		}
		return lCompany;
	}

	public boolean deleteCompany(int id) {
		int resultQuery1 = 0;
		int resultQuery2 = 0;
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement preparedStatement1 = conn.prepareStatement(QUERY_DELETE_COMPUTER_WITH_COMPANY_ID);
				PreparedStatement preparedStatement2 = conn.prepareStatement(QUERY_DELETE_COMPANY);) {
			conn.setAutoCommit(false);
			preparedStatement1.setInt(1, id);
			preparedStatement2.setInt(1, id);
			resultQuery1 = preparedStatement1.executeUpdate();
			resultQuery2 = preparedStatement2.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return (resultQuery1 > 0 && resultQuery2 > 0) ? true : false;
	}
}
