package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties("spring.datasource.datasource01")
public class DataSourceProperties01 extends AbstractDataSourceProperties {
}
