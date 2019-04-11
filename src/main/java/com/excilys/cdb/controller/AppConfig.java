package com.excilys.cdb.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan({"com.excilys.cdb.dao","com.excilys.cdb.services"})
public class AppConfig {
	
	@Bean
	public HikariDataSource dataSource() {
		return new HikariDataSource(new HikariConfig("/database.properties"));
	}
}
