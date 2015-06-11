package com.job.configuration;

import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

public class EntityManagerFactory {

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
