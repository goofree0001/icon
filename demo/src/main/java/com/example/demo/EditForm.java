package com.example.demo;

import lombok.Data;

@Data
public class EditForm {

	private String path;

	private Integer pkid;
	private String name;
	private Integer wins;
	private Integer best;
	private Float size;
}
