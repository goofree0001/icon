package com.example.demo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DemoMapper {

	@Select("SELECT name FROM tourneys LIMIT 1")
	public String read1();

	@Select("SELECT column2 FROM newtable LIMIT 1")
	public String read2();

}
