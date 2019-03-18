package com.excilys.cdb.dao;

import java.io.*;
import java.util.Properties;

import com.excilys.cdb.interfaces.IDAOCompany;
import com.excilys.cdb.interfaces.IDAOComputer;

public class DAOFactory {

	private static Properties properties = new Properties();

	/**
	 * static bloc that will load the properties of the database only once
	 * and store them in the properties variable
	 */
	static {
		try (FileInputStream in = new FileInputStream("src/main/ressources/database.properties")) {
			properties.load(in);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Allows you to chose an implementation of DAOCompany (jdbc or hibernate)
	 * @param type the type of implementation you need
	 * @return the DAOCompany Object
	 */
	public static IDAOCompany getDAOCompany(String type) {
		if (type.equals("jdbc")) {
			return new JDBCCompany(properties);
		} else if (type.equals("hibernate")) {
			return null;
		}
		return null;
	}

	/**
	 * Allows you to chose an implementation of DAOComputer (jdbc or hibernate)
	 * @param type the type of implementation you need
	 * @return the DAOComputer Object
	 */
	public static IDAOComputer getDAOComputer(String type) {
		if (type.equals("jdbc")) {
			return new JDBCComputer(properties);
		} else if (type.equals("hibernate")) {
			return null;
		}
		return null;
	}
}
