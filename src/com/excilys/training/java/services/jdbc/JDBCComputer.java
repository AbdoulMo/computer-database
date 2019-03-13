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
	public Computer getComputer(int id) {
		Computer c = new Computer();
		query = "SELECT * FROM computer WHERE id = ?";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
				c.setIntroduced(resultSet.getDate("introduced"));
				c.setDiscontinued(resultSet.getDate("discontinued"));
				c.setManufacturer_id(resultSet.getInt("company_id"));
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
		List<Computer> lComputer = new ArrayList<>();
		query = "SELECT * FROM computer";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Computer c = new Computer();
				c.setId(resultSet.getInt("id"));
				c.setName(resultSet.getString("name"));
				c.setIntroduced(resultSet.getDate("introduced"));
				c.setDiscontinued(resultSet.getDate("discontinued"));
				c.setManufacturer_id(resultSet.getInt("company_id"));
				lComputer.add(c);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCConnection.disconnection();
		}
		return lComputer;
	}

	@Override
	public int addComputer(Computer c) {
		int result = 0;
		query = "INSERT INTO computer (name, introduced, discontinued, company_id)" + " VALUES (?,?,?,?)";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate(2, c.getIntroduced());
			preparedStatement.setDate(3, c.getDiscontinued());
			preparedStatement.setInt(4, c.getManufacturer_id());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCConnection.disconnection();
		}
		return result;
	}

	@Override
	public int deleteComputer(Computer c) {
		int result = 0;
		query = "DELETE FROM computer WHERE id = ?";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setInt(1, c.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCConnection.disconnection();
		}
		return result;
	}

	@Override
	public int updateComputer(Computer c) {
		int result = 0;
		query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,company_id = ? WHERE id = ?";
		try {
			preparedStatement = JDBCConnection.connection().prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate(2, c.getIntroduced());
			preparedStatement.setDate(3, c.getDiscontinued());
			preparedStatement.setInt(4, c.getManufacturer_id());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCConnection.disconnection();
		}
		return result;
	}
}
