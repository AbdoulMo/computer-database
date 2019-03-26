//package com.excilys.cdb.dao;
//
//import static org.junit.Assert.*;
//
//import org.junit.Test;
//
//import com.excilys.cdb.interfaces.IDAOCompany;
//import com.excilys.cdb.interfaces.IDAOComputer;
//
//public class TestDAOFactory {
//
//	@Test
//	public void testGetDAOCompany() {
//		IDAOCompany daoCompany = DatabaseParam.getDAOCompany("jdbc");
//		assertNotNull(daoCompany);
//
//		IDAOCompany daoCompany2 = DatabaseParam.getDAOCompany("fghjk");
//		assertNull(daoCompany2);
//	}
//
//	@Test
//	public void testGetDAOComputer() {
//		IDAOComputer daoComputer = DatabaseParam.getDAOComputer("jdbc");
//		assertNotNull(daoComputer);
//
//		IDAOComputer daoComputer2 = DatabaseParam.getDAOComputer("azert");
//		assertNull(daoComputer2);
//	}
//
//}
