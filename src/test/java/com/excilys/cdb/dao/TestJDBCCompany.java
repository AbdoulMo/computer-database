package com.excilys.cdb.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.cdb.interfaces.IDAOCompany;
import com.excilys.cdb.model.Company;

public class TestJDBCCompany {

	private static IDAOCompany jdbcCompany;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		jdbcCompany = DatabaseParam.getDAOCompany("jdbc");
	}

	@Test
	public void testGetCompany() {
		Company c1 = jdbcCompany.getCompany(1);
		assertEquals(1, c1.getId());
		assertEquals("Apple Inc.", c1.getName());

		Company c2 = jdbcCompany.getCompany(-1);
		assertEquals(0, c2.getId());
		assertNull(c2.getName());

		Company c3 = jdbcCompany.getCompany(1_000_000);
		assertEquals(0, c3.getId());
		assertNull(c3.getName());
	}

	@Test
	public void testGetAllCompany() {
		ArrayList<Company> companyList = jdbcCompany.getAllCompany();
		assertEquals(42, companyList.size());
	}

}
