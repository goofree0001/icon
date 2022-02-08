package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DemoMapper;

@Service
public class DemoService {

	@Autowired
	DemoMapper demoMapper;

	public String read1(){
		String temp_str = demoMapper.read1();
		return temp_str;
	}
}
