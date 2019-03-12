package com.excilys.training.java.services.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.excilys.training.java.modele.Computer;
import com.excilys.training.java.services.interfaces.IDAOComputer;

public class JDBCComputer implements IDAOComputer {

	private String query;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public Computer getComputer(String name) {
		Computer c = new Computer();
		query = "SELECT * FROM computer WHERE name = ?";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
				c.setIntroduced(resultSet.getDate("introduced"));
				c.setDiscontinued(resultSet.getDate("discontinued"));
				c.setManufacturer_id(resultSet.getInt("manufacturer_id"));
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
	public List<Computer> getAllComputers() {
		List<Computer> cList = new ArrayList<>();
		Computer c = new Computer();
		query = "SELECT * FROM computer";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
				c.setIntroduced(resultSet.getDate("introduced"));
				c.setDiscontinued(resultSet.getDate("discontinued"));
				c.setManufacturer_id(resultSet.getInt("manufacturer_id"));
				cList.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCConnection.disconnection();
		}
		return cList;
	}

	@Override
	public boolean addComputer(Computer c) {
		String s = "Naissance)";
		query = "INSERT INTO computer (name, introduced, discontinued, company_id)"
				+ " VALUES (?,?,?,?)";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean deleteComputer(Computer c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateComputer(Computer c) {
		// TODO Auto-generated method stub
		return false;
	}
}
