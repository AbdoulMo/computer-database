package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.interfaces.IDAOCompany;
import com.excilys.cdb.modele.Company;

public class JDBCCompany implements IDAOCompany {

	private final String URL = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	private final String USERNAME = "admincdb";
	private final String PASSWORD = "qwerty1234";
	
	private String query;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public Company getCompany(int id) {
		Company c = new Company();
		query = "SELECT * FROM company WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public List<Company> getAllCompany() {
		List<Company> lCompany = new ArrayList<>();
		query = "SELECT * FROM company";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)){
			preparedStatement = conn.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Company c = new Company();
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
				lCompany.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lCompany;
	}
}
