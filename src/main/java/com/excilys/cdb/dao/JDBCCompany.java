package com.excilys.cdb.dao;

import java.sql.*;
import java.util.*;

import com.excilys.cdb.interfaces.IDAOCompany;
import com.excilys.cdb.modele.Company;


public class JDBCCompany implements IDAOCompany {

	private Properties properties;
	private static final String QUERY_GET_COMPANY = "SELECT * FROM company WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPANY = "SELECT * FROM company";
	private ResultSet resultSet;

	public JDBCCompany(Properties props) {
		this.properties = props;
	}

	@Override
	public Company getCompany(int id) {
		Company company = new Company();
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_COMPANY);) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				company.setId(resultSet.getInt("id"));
				company.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return company;
	}

	@Override
	public ArrayList<Company> getAllCompany() {
		ArrayList<Company> lCompany = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_ALL_COMPANY);) {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company();
				company.setId(resultSet.getInt("id"));
				company.setName(resultSet.getString("name"));
				lCompany.add(company);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lCompany;
	}
}
