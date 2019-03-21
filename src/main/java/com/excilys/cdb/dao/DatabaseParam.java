package com.excilys.cdb.dao;

import java.io.*;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DatabaseParam {

	private static Properties properties;
	private String JDBCurl;
	private String username;
	private String password;
	private final static Logger logger = Logger.getLogger(DatabaseParam.class);
	private final static DatabaseParam _instance = new DatabaseParam();

	private DatabaseParam() {
		try (FileInputStream in = new FileInputStream("src/main/ressources/database.properties")) {
			properties.load(in);
		} catch (FileNotFoundException e) {
			logger.error("Cannot find property file for database.", e);
		} catch (IOException e) {
			logger.error("Cannot load or close property file stream.", e);
		}
		this.JDBCurl = properties.getProperty("jdbc.url");
		this.username = properties.getProperty("username");
		this.password = properties.getProperty("password");
	}

	public static DatabaseParam getInstance() {
		return _instance;
	}

	public String getJDBCurl() {
		return JDBCurl;
	}

	public void setJDBCurl(String jDBCurl) {
		JDBCurl = jDBCurl;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
