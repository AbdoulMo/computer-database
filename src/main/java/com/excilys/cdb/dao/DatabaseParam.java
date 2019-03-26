package com.excilys.cdb.dao;

import java.io.*;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DatabaseParam {

	private static Properties properties = new Properties();
	private String JDBCurl;
	private String username;
	private String password;
	private final static Logger logger = Logger.getLogger(DatabaseParam.class);
	private final static DatabaseParam _instance = new DatabaseParam();

	private DatabaseParam() {
		try (InputStream in = DatabaseParam.class.getResourceAsStream("/database.properties")) {
			properties.load(in);
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.JDBCurl = properties.getProperty("jdbc.url");
			this.username = properties.getProperty("username");
			this.password = properties.getProperty("password");
		} catch (FileNotFoundException e) {
			logger.error("Cannot find property file for database.", e);
		} catch (IOException e) {
			logger.error("Cannot load or close property file stream.", e);
		} catch (ClassNotFoundException e) {
			logger.error("Cannot load mysql Driver", e);
		}		
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
