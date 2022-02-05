package com.example.demo;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.apache.tomcat.jdbc.pool.DataSource;

@Data
public class AbstractDataSourceProperties {

	protected String url;
	protected String username;
	protected String password;
	protected String driverClassName;

	@Bean
	public DataSource createDataSourceBean() {
		DataSource dataSource = new DataSource();

		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);

		return dataSource;
	}
}
