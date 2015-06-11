package com.job.configuration;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceBuilder {

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
