package com.excilys.training.java.services.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.training.java.modele.Company;
import com.excilys.training.java.services.interfaces.IDAOCompany;

public class JDBCCompany implements IDAOCompany {

	private String query;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public Company getCompany(int id) {
		Company c = new Company();
		query = "SELECT * FROM company WHERE id = ?";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCConnection.disconnection();
		}
		return c;
	}

	@Override
	public List<Company> getAllCompany() {
		List<Company> lCompany = new ArrayList<>();
		query = "SELECT * FROM company";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
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
		} finally {
			JDBCConnection.disconnection();
		}
		return lCompany;
	}
}
