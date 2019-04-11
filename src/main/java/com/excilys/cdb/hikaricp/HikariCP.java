package com.excilys.cdb.hikaricp;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {
	
	private static HikariConfig config = new HikariConfig("/database.properties");
	private static HikariDataSource ds;
	private final static HikariCP _instance = new HikariCP();

	private HikariCP() {
		ds = new HikariDataSource(config);
	}

	public static HikariCP getInstance() {
		return _instance;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
