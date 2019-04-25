package com.excilys.cdb.springconfig;

import java.util.Locale;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = { "com.excilys.cdb.springconfig", "com.excilys.cdb.controller.web",
		"com.excilys.cdb.services" })
@PropertySource("classpath:database.properties")
@EnableJpaRepositories("com.excilys.cdb.dao")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {

	private Environment environment;

	public SpringConfig(Environment environment) {
		this.environment = environment;
	}

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/web-ressources/**").addResourceLocations("/web-ressources/");
	}

	@Bean
	public InternalResourceViewResolver resolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/web-ressources/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	/*---JPA Config---*/
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
	/*---end JPA Config---*/

	/*---I18N Config---*/
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		localeResolver.setDefaultLocale(Locale.FRANCE);
		localeResolver.setCookieMaxAge(3600);
		return localeResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry interceptorRegistry) {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("lang");
		interceptorRegistry.addInterceptor(localeChangeInterceptor);
	}
	/*---end I18N Config---*/

}
