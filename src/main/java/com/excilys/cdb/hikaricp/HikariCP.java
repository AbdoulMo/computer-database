package com.excilys.cdb.hikaricp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCP {

	private static Properties properties = new Properties();
	private String JDBCurl;
	private String username;
	private String password;
	private final static Logger logger = Logger.getLogger(HikariCP.class);

	
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	private final static HikariCP _instance = new HikariCP();

	private HikariCP() {
		try (InputStream in = HikariCP.class.getResourceAsStream("/database.properties")) {
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
		
		config.setJdbcUrl(this.JDBCurl);
		config.setUsername(this.username);
		config.setPassword(this.password);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	public static HikariCP getInstance() {
		return _instance;
	}

	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}
