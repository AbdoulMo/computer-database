package com.excilys.cdb.dao;

import java.util.*;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.excilys.cdb.model.Computer;

public class JDBCComputer {

	private final Logger logger = Logger.getLogger(JDBCComputer.class);
	private static final String QUERY_GET_COMPUTER_BY_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
	private static final String QUERY_GET_ALL_COMPUTERS = "SELECT id, name, introduced, discontinued, company_id FROM computer";
	private static final String QUERY_ADD_COMPUTER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String QUERY_DELETE_COMPUTER = "DELETE FROM computer WHERE id = ?";
	private static final String QUERY_UPDATE_COMPUTER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?,company_id = ? WHERE id = ?";
	private JdbcTemplate jdbcTemplate;

	public JDBCComputer(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public Optional<Computer> getComputerByID(int id) {
		Computer computer = null;
		try {
			computer = (Computer) jdbcTemplate.queryForObject(QUERY_GET_COMPUTER_BY_ID, new Object[] { id },
					new BeanPropertyRowMapper<Computer>(Computer.class));
		} catch (DataAccessException e) {
			logger.error("Cannot get computer with specified ID", e);
		}
		return Optional.ofNullable(computer);
	}

	public ArrayList<Computer> getAllComputers() {
		ArrayList<Computer> computerList = new ArrayList<>();
		try {
			computerList = (ArrayList<Computer>) jdbcTemplate.query(QUERY_GET_ALL_COMPUTERS,
					new BeanPropertyRowMapper<Computer>(Computer.class));
		} catch (DataAccessException e) {
			logger.error("Cannot get all computer", e);
		}
		return computerList;
	}

	public boolean addComputer(Computer computer) {
		int result = 0;
		try {
			result = jdbcTemplate.update(QUERY_ADD_COMPUTER, computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(),
					computer.getManufacturer_id() == 0 ? null : computer.getManufacturer_id());
		} catch (DataAccessException e) {
			logger.error("Unable to add a new computer", e);
		}
		return result == 1 ? true : false;
	}

	public boolean deleteComputer(int id) {
		int result = 0;
		try {
			result = jdbcTemplate.update(QUERY_DELETE_COMPUTER, id);
		} catch (DataAccessException e) {
			logger.error("Unable to delete computer", e);
		}
		return result == 1 ? true : false;
	}

	public boolean updateComputer(Computer computer) {
		int result = 0;
		try {
			result = jdbcTemplate.update(QUERY_UPDATE_COMPUTER, computer.getName(), computer.getIntroduced(),
					computer.getDiscontinued(), computer.getManufacturer_id(), computer.getId());
		} catch (DataAccessException e) {
			logger.error("Unable to update computer", e);
		}
		return result == 1 ? true : false;
	}
}
