package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.interfaces.IDAOComputer;
import com.excilys.cdb.model.Computer;

public class TestJDBCComputerUpdate {

	private static IDAOComputer jdbcComputer;
	ArrayList<Computer> computerList;
	private static Computer computerToUpdate;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdbcComputer = DatabaseParam.getDAOComputer("jdbc");
	}

	@Before
	public void setUp() throws Exception {
		Date date = new Date(System.currentTimeMillis());
		Computer computer = new Computer();
		computer.setName("Computer name before test");
		computer.setIntroduced(date);
		computer.setDiscontinued(date);
		computer.setManufacturer_id(4);

		jdbcComputer.addComputer(computer);

		computerList = jdbcComputer.getAllComputers();
		computerToUpdate = computerList.get(computerList.size() - 1);
	}

	@After
	public void tearDown() throws Exception {
		jdbcComputer.deleteComputer(computerToUpdate.getId());
	}

	@Test
	public void test() {
		computerToUpdate.setName("Updated Name");
		jdbcComputer.updateComputer(computerToUpdate);
		Computer computer = jdbcComputer.getComputer(computerToUpdate.getId());
		assertEquals("Updated Name", computer.getName());
		assertEquals(new Date(System.currentTimeMillis()).toString(), computer.getIntroduced().toString());
		assertEquals(new Date(System.currentTimeMillis()).toString(), computer.getDiscontinued().toString());
		assertEquals(4, computer.getManufacturer_id());
	}

}
