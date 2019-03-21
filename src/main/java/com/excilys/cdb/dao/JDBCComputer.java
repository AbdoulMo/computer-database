package com.excilys.cdb.dao;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.model.ComputerMapper;

public class JDBCComputer {

	private DatabaseParam databaseParam = DatabaseParam.getInstance();
	private final static Logger logger = Logger.getLogger(JDBCCompany.class);
	private static final String QUERY_GET_COMPUTER_BY_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPUTERS = "SELECT id, name, introduced, discontinued, company_id FROM computer";
	private static final String QUERY_ADD_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,company_id = ? WHERE id = ?";
	private ResultSet resultSet;

	public Optional<Computer> getComputerByID(int id) {
		Computer computer = null;
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_COMPUTER_BY_ID);) {
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			computer = ComputerMapper.resultSetToComputer(resultSet);
		} catch (SQLException e) {
			logger.error("Error while trying to get computer with specified ID !", e);
		}
		return Optional.ofNullable(computer);
	}

	public ArrayList<Computer> getAllComputers() {
		ArrayList<Computer> lComputer = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_GET_ALL_COMPUTERS);) {
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Computer computer = ComputerMapper.resultSetToComputer(resultSet);
				lComputer.add(computer);
			}
		} catch (SQLException e) {
			logger.error("Error while trying to get all computer !", e);
		}
		return lComputer;
	}

	public boolean addComputer(Computer computer) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_ADD_COMPUTER);) {
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinued());
			if (computer.getManufacturer_id() == 0) {
				preparedStatement.setBinaryStream(4, null);
			} else {
				preparedStatement.setInt(4, computer.getManufacturer_id());
			}
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while trying to add a new computer !", e);
		}
		return result == 1 ? true : false;
	}

	public boolean deleteComputer(int id) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_DELETE_COMPUTER);) {
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while trying to delete a computer !", e);
		}
		return result == 1 ? true : false;
	}

	public boolean updateComputer(Computer computer) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(databaseParam.getJDBCurl(), databaseParam.getUsername(),
				databaseParam.getPassword());
				PreparedStatement preparedStatement = conn.prepareStatement(QUERY_UPDATE_COMPUTER);) {
			preparedStatement.setString(1, computer.getName());
			preparedStatement.setDate(2, computer.getIntroduced());
			preparedStatement.setDate(3, computer.getDiscontinued());
			preparedStatement.setInt(4, computer.getManufacturer_id());
			preparedStatement.setInt(5, computer.getId());
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while trying to update a computer !", e);
		}
		return result == 1 ? true : false;
	}
}
