package com.job.configuration;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("com.job")
@PropertySource("classpath:dataSource/develop.xml")
public class RepositoryConfig {
	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() throws SQLException {
		return new DataSourceBuilder().build(environment);
	}

	@Bean
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		return new HibernateJpaVendorAdapter();
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
		return new EntityManagerFactory().build(environment, dataSource(), jpaVendorAdapter());
	}

	@Bean
	public JpaTransactionManager transactionManager() throws SQLException {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}

	private static class DataSourceBuilder {
		private static final String URL = "arbeit.mysql.jdbc.url";
		private static final String USER_NAME = "arbeit.mysql.jdbc.username";
		private static final String USER_PASSWORD = "arbeit.mysql.jdbc.password";

		public DataSource build(Environment environment) throws SQLException {
			SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
			dataSource.setDriver(new com.mysql.jdbc.Driver());
			dataSource.setUrl(environment.getProperty(URL));
			dataSource.setUsername(environment.getProperty(USER_NAME));
			dataSource.setPassword(environment.getProperty(USER_PASSWORD));

			return dataSource;
		}
	}

	private static class EntityManagerFactory {
		private static final String ENTITY_SCAN = "entitymanager.packages.to.scan";
		private static final String HIBERNATE_DIALECT = "hibernate.dialect";
		private static final String HIBERNATE_SHOW_SQL = "hibernate.show_sql";

		public LocalContainerEntityManagerFactoryBean build(Environment environment, DataSource dataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
			LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactoryBean.setDataSource(dataSource);
			entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
			entityManagerFactoryBean.setPackagesToScan(environment.getRequiredProperty(ENTITY_SCAN));
			entityManagerFactoryBean.setJpaProperties(hibProperties(environment));

			return entityManagerFactoryBean;
		}

		private Properties hibProperties(Environment environment) {
			Properties properties = new Properties();
			properties.put(HIBERNATE_DIALECT, environment.getRequiredProperty(HIBERNATE_DIALECT));
			properties.put(HIBERNATE_SHOW_SQL, environment.getRequiredProperty(HIBERNATE_SHOW_SQL));
			return properties;
		}
	}

}
