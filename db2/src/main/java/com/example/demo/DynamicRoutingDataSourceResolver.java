package com.example.demo;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.example.demo.SchemaContextHolder;

public class DynamicRoutingDataSourceResolver extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		switch (SchemaContextHolder.getSchemaType()) {
		case DATASOURCE01:
			return "datasource01";
		case DATASOURCE02:
			return "datasource02";
		default:
			return "datasource01";
		}
	}
}

