// https://qiita.com/syukai/items/0d4bf27f82fef9965cdd

package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.DemoService;
import com.example.demo.SchemaContextHolder;
import com.example.demo.SchemaType;

@RestController
@Component
public class DemoController {

	@Autowired
	DemoService demoService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		String temp_str = "";

		SchemaContextHolder.setSchemaType(SchemaType.DATASOURCE01);
		temp_str = temp_str + demoService.read1() + "<br>";

		SchemaContextHolder.setSchemaType(SchemaType.DATASOURCE02);
		temp_str = temp_str + demoService.read2() + "<br>";

		return temp_str;
	}
}
