package com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


public class HikariCP {

	private static HikariConfig config = new HikariConfig("/database.properties");
	
	private HikariDataSource ds;

	private HikariCP() {
		ds = new HikariDataSource(config);
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
