package com.excilys.cdb.dao;

import java.sql.*;
import java.util.*;

import com.excilys.cdb.interfaces.IDAOComputer;
import com.excilys.cdb.modele.Computer;

public class JDBCComputer implements IDAOComputer {

	private Properties properties;
	private static final String QUERY_GET_COMPUTER = "SELECT * FROM computer WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPUTERS = "SELECT * FROM computer";
	private static final String QUERY_ADD_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,company_id = ? WHERE id = ?";
	private ResultSet resultSet;

	public JDBCComputer(Properties props) {
		this.properties = props;
	}

	@Override
	public Computer getComputer(int id) {
		Computer c = new Computer();
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_COMPUTER);) {
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
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_ALL_COMPUTERS);) {
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
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_ADD_COMPUTER);) {
			preparedStatement.setString(1, c.getName());
			preparedStatement.setDate(2, c.getIntroduced());
			preparedStatement.setDate(3, c.getDiscontinued());
			if (c.getManufacturer_id() == 0) {
				preparedStatement.setBinaryStream(4, null);
			} else {
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
	public int deleteComputer(int id) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_DELETE_COMPUTER);) {
			preparedStatement.setInt(1, id);
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
		try (Connection conn = DriverManager.getConnection(properties.getProperty("jdbc.url"),
				properties.getProperty("jdbc.username"), properties.getProperty("jdbc.password"));
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_UPDATE_COMPUTER);) {
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
