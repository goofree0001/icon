// gradlew bootrun
// gradle bootrun

package com.example.demo;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.DemoService;

// @RestController
@Controller
public class DemoController {

	@Autowired
	DemoService demoService;

// localhost:8080
	@RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.GET})
	public String index(Model model) throws Exception {

		Pattern pattern;
		Matcher matcher;

//		pattern = Pattern.compile("title='(.*?)'");
//		matcher = pattern.matcher(data_class.data);
//		if (matcher.find()) {
//			System.out.println("title:" + matcher.group(1));
//		}

		String temp_str;

		temp_str = demoService.read1();
		System.out.println("temp_str:" + temp_str);
		model.addAttribute("temp_str",temp_str);

		return "index.html";
	}
}
