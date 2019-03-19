package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.interfaces.IDAOComputer;
import com.excilys.cdb.modele.Computer;

public class TestJDBCComputer {

	private static IDAOComputer jdbcComputer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdbcComputer = DAOFactory.getDAOComputer("jdbc");
	}

	@Test
	public void testGetComputer() {
		Computer c1 = jdbcComputer.getComputer(12);
		assertEquals(12, c1.getId());
		assertEquals("Apple III", c1.getName());
		assertEquals("1980-05-01", c1.getIntroduced().toString());
		assertEquals("1984-04-01", c1.getDiscontinued().toString());
		assertEquals(1, c1.getManufacturer_id());

		Computer c2 = jdbcComputer.getComputer(-1);
		assertEquals(0, c2.getId());
		assertNull(c2.getName());

		Computer c3 = jdbcComputer.getComputer(1_000_000);
		assertEquals(0, c3.getId());
		assertNull(c3.getName());
	}

	@Test
	public void testGetAllComputers() {
		ArrayList<Computer> computerList = jdbcComputer.getAllComputers();
		assertEquals(551, computerList.size());
	}

//	@Test
//	public void testAddComputer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDeleteComputer() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testUpdateComputer() {
//		fail("Not yet implemented");
//	}

}
