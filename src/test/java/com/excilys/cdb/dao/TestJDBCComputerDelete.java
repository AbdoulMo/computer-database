package com.excilys.cdb.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.interfaces.IDAOComputer;
import com.excilys.cdb.modele.Computer;

public class TestJDBCComputerDelete {

	private static IDAOComputer jdbcComputer;
	private static Computer computer = new Computer();
	private static ArrayList<Computer> computerList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdbcComputer = DAOFactory.getDAOComputer("jdbc");
	}

	@Before
	public void setUp() throws Exception {
		computerList = jdbcComputer.getAllComputers();

		Date date = new Date(System.currentTimeMillis());
		computer.setName("Delete Test");
		computer.setIntroduced(date);
		computer.setDiscontinued(date);
		computer.setManufacturer_id(4);

		jdbcComputer.addComputer(computer);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteComputer() {
		Computer c = jdbcComputer.getAllComputers().get(computerList.size());
		jdbcComputer.deleteComputer(c.getId());

		assertEquals(computerList.size(), jdbcComputer.getAllComputers().size());
	}

}
