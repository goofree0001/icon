package com.example.demo;

import org.springframework.util.Assert;

public class SchemaContextHolder {
	private static ThreadLocal<SchemaType> contextHolder = new ThreadLocal<SchemaType>();

	public static void setSchemaType(SchemaType type) {
		Assert.notNull(type, "Schema type cannot be null.");
		contextHolder.set(type);
	}

	public static SchemaType getSchemaType() {
		return contextHolder.get();
	}

	public static void clear() {
		contextHolder.remove();
	}
}
