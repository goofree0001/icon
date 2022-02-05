package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.DynamicRoutingDataSourceResolver;

@Component
@Configuration
public class DataSourceConfig {

	@Autowired
	DataSourceProperties01 dataSourceP01;

	@Autowired
	DataSourceProperties02 dataSourceP02;

	@Bean
	@Primary
	public DynamicRoutingDataSourceResolver dataSource() {
	DynamicRoutingDataSourceResolver resolver = new DynamicRoutingDataSourceResolver();

	Map<Object, Object> dataSources = new HashMap<Object,Object>();
	dataSources.put("datasource01", dataSourceP01.createDataSourceBean());
	dataSources.put("datasource02", dataSourceP02.createDataSourceBean());

	resolver.setTargetDataSources(dataSources);

	resolver.setDefaultTargetDataSource(dataSourceP01.createDataSourceBean());

	return resolver;
	}
}
