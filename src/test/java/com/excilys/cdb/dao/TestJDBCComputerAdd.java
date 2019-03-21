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

public class TestJDBCComputerAdd {

	private static IDAOComputer jdbcComputer;
	ArrayList<Computer> computerList;
	Computer computer = new Computer();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdbcComputer = DatabaseParam.getDAOComputer("jdbc");
	}

	@Before
	public void setUp() throws Exception {
		Date date = new Date(System.currentTimeMillis());
		computer.setName("Computer name before test");
		computer.setIntroduced(date);
		computer.setDiscontinued(date);
		computer.setManufacturer_id(4);
	}

	@After
	public void tearDown() throws Exception {
		jdbcComputer.deleteComputer(computerList.get(computerList.size() - 1).getId());
	}

	@Test
	public void testAddComputer() {
		jdbcComputer.addComputer(computer);

		computerList = jdbcComputer.getAllComputers();
		Computer c = computerList.get(computerList.size() - 1);
		assertEquals(computer.getName(), c.getName());
		assertEquals(computer.getIntroduced().toString(), c.getIntroduced().toString());
		assertEquals(computer.getDiscontinued().toString(), c.getDiscontinued().toString());
		assertEquals(computer.getManufacturer_id(), c.getManufacturer_id());
	}

}
