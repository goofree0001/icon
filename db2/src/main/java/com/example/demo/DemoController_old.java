/*
package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.boot.context.properties.ConfigurationProperties;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration; 
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration; 

import com.example.demo.DemoService;

@RestController
@Component
public class DemoController {

	@Autowired
	DemoService demoService;

	@Bean
//	@Primary
	@ConfigurationProperties(prefix="spring.datasource.primary")
	public DataSourceProperties primaryDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
//	@Primary
	@ConfigurationProperties("spring.datasource.primary.configuration")
	public BasicDataSource primaryDataSource(
	@Qualifier("primaryDataSourceProperties") DataSourceProperties primaryDataSourceProperties) {
		return primaryDataSourceProperties.initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.secondary")
	public DataSourceProperties secondaryDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	@Primary
	@ConfigurationProperties("spring.datasource.secondary.configuration")
	public BasicDataSource secondaryDataSource(
	@Qualifier("secondaryDataSourceProperties") DataSourceProperties secondaryDataSourceProperties) {
		return secondaryDataSourceProperties.initializeDataSourceBuilder().type(BasicDataSource.class).build();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		String temp_str = "";

//		SchemaContextHolder.setSchemaType(SchemaType.primary);
//		temp_str = temp_str + demoService.read1();

//		SchemaContextHolder.setSchemaType(SchemaType.secondary);
		temp_str = temp_str + demoService.read2();

		return temp_str;
	}
}
*/
