package com.excilys.cdb.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.excilys.cdb.dao.JDBCCompany;
import com.excilys.cdb.vue.Cli;

public class Launcher {

	public static void main(String[] args) {
//		@SuppressWarnings("resource")
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//		JDBCCompany daoCompany = (JDBCCompany) applicationContext.getBean("daoCompany");
//		System.out.println(daoCompany.getAllCompany());
		ControllerCLI controllerCLI = new ControllerCLI();
		controllerCLI.runApp();
	}

}
