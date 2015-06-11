package com.job.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
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
}
