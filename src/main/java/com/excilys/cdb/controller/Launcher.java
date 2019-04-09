package com.excilys.cdb.controller;

import com.excilys.cdb.dao.JDBCCompany;

public class Launcher {

	public static void main(String[] args) {
		JDBCCompany daoCompany = new JDBCCompany();
		System.out.println(daoCompany.deleteCompany(26));
	}

}
