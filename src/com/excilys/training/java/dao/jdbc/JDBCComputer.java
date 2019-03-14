package com.excilys.training.java.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.training.java.dao.interfaces.IDAOComputer;
import com.excilys.training.java.modele.Computer;

public class JDBCComputer implements IDAOComputer {

	private final String URL = "jdbc:mysql://localhost:3306/computer-database-db?useSSL=false";
	private final String USERNAME = "admincdb";
	private final String PASSWORD = "qwerty1234";

	private String query;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	@Override
	public Computer getComputer(int id) {
		Computer c = new Computer();
		query = "SELECT * FROM computer WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			preparedStatement = conn.prepareStatement(query);
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
		}
		return c;
	}

	@Override
	public ArrayList<Computer> getAllComputers() {
		ArrayList<Computer> lComputer = new ArrayList<>();
		query = "SELECT * FROM computer";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			preparedStatement = conn.prepareStatement(query);
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
		}
		return lComputer;
	}

	@Override
	public int addComputer(Computer c) {
		int result = 0;
		query = "INSERT INTO computer (name, introduced, discontinued, company_id)" + " VALUES (?,?,?,?)";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate(2, c.getIntroduced());
			preparedStatement.setDate(3, c.getDiscontinued());
			if(c.getManufacturer_id() == 0) {
				preparedStatement.setBinaryStream(4, null);
			}else {
				preparedStatement.setInt(4, c.getManufacturer_id());
			}
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteComputer(Computer c) {
		int result = 0;
		query = "DELETE FROM computer WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, c.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateComputer(Computer c) {
		int result = 0;
		query = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,company_id = ? WHERE id = ?";
		try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate(2, c.getIntroduced());
			preparedStatement.setDate(3, c.getDiscontinued());
			preparedStatement.setInt(4, c.getManufacturer_id());
			preparedStatement.setInt(5, c.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
