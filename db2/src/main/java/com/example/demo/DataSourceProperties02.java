package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties("spring.datasource.datasource02")
public class DataSourceProperties02 extends AbstractDataSourceProperties {
}
