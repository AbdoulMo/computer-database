package com.excilys.cdb.persistence.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.excilys.cdb.config"})
@PropertySource("classpath:database.properties")
@EnableJpaRepositories("com.excilys.cdb.persistence.dao")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {

	private Environment environment;

	public SpringConfig(Environment environment) {
		this.environment = environment;
	}

	// Configure the entity manager factory bean
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactory.setDataSource(dataSource());
		entityManagerFactory.setPackagesToScan(new String[] { "com.excilys.cdb.model" });
		// Create default configuration for Hibernate
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaProperties(additionalProperties());

		return entityManagerFactory;
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("javax.persistence.schema-generation.database.action", "none");
		properties.setProperty("hibernate.ejb.use_class_enhancer", "true");
		return properties;
	}

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName(environment.getRequiredProperty("driverClassName"));
		config.setJdbcUrl(environment.getRequiredProperty("jdbcUrl"));
		config.setUsername(environment.getRequiredProperty("dataSource.user"));
		config.setPassword(environment.getRequiredProperty("dataSource.password"));
		config.addDataSourceProperty("cachePrepStmts", environment.getRequiredProperty("dataSource.cachePrepStmts"));
		config.addDataSourceProperty("prepStmtCacheSize", environment.getRequiredProperty("dataSource.prepStmtCacheSize"));
		config.addDataSourceProperty("prepStmtCacheSqlLimit", environment.getRequiredProperty("dataSource.prepStmtCacheSqlLimit"));

		HikariDataSource dataSource = new HikariDataSource(config);
		return dataSource;
	}

	// Configure the transaction manager bean
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}

}
