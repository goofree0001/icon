/*
https://qiita.com/Haru_3/items/eba8fdb296aa809e9546
https://learning-collection.com/springboot%e5%85%a5%e9%96%80/

https://qiita.com/k-tabuchi/items/81fa2c774f92c63125b5
Spring アップロードファイルを読み込んで中身を表示する方法

https://yossan.hatenablog.com/entry/2019/05/25/123853
InputStreamReader 例
OutputStreamWriter 例

https://qiita.com/riekure/items/31c1a9e371c9020a4973
ファイル書き込み機能を実装してみた



*/


package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional; 
import java.util.Map;
import java.util.LinkedHashMap;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.Reader;
import java.io.BufferedReader;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.DemoService;
import com.example.demo.DemoEntity;
import com.example.demo.EditForm;
import com.example.demo.MainForm;
import com.example.demo.MainFormList;

@Controller
public class DemoController {

	@Autowired
	DemoService demoService;

	@RequestMapping(path = "/{path}", method = {RequestMethod.GET, RequestMethod.POST})
	public String main(
	@PathVariable String path,
	@RequestParam(name = "mode", required = false) String mode,
	MainForm mainForm,
	EditForm editForm
//	Model model
	) {

	System.out.println("mode:" + mode);

	DemoEntity temp_entity;
	Integer mainFormList_pkid;

	if ("main".equals(path)) {
		List<DemoEntity> list_findAll = demoService.findAll();

		List<MainFormList> temp_mainFormList = new ArrayList<MainFormList>();
		list_findAll.forEach(list_detail -> {
			MainFormList list_temp = new MainFormList();
			list_temp.setEntity(list_detail);
			temp_mainFormList.add(list_temp);
		});

		mainForm.setMainFormList(temp_mainFormList);

		return "index.html";
	}

	if ("create".equals(path)) {
		switch (mode) {
		case "create":
			editForm = new EditForm();
			temp_entity = new DemoEntity();
			editForm.setEntity(temp_entity);
			editForm.setPath(path);
			return "edit.html";
		case "regist":
			temp_entity = editForm.getEntity();
			demoService.create(temp_entity);
			return "redirect:/main";
		case "back":
			return "redirect:/main";
		default:
			return "redirect:/main";
		}
	}

	if ("edit".equals(path)) {
		switch (mode) {
		case "edit":

			if (mainForm.getMainFormList() == null) {
					return "redirect:/main";
			}

			mainFormList_pkid = 0;
			for (MainFormList mainFormList : mainForm.getMainFormList()) {
				if (mainFormList.getSelectCheckboxs().length > 0) {
					mainFormList_pkid = mainFormList.getSelectCheckboxs()[0];
					break;
				}
			}

			if (mainFormList_pkid == 0) {
				return "redirect:/main";
			}
			temp_entity = demoService.findById(mainFormList_pkid);
			editForm.setEntity(temp_entity);
			editForm.setPath(path);
			return "edit.html";
		case "regist":
			temp_entity = editForm.getEntity();
			demoService.update(temp_entity);
			return "redirect:/main";
		case "back":
			return "redirect:/main";
		default:
			return "redirect:/main";
		}
	}

	if ("delete".equals(path)) {

		if (mainForm.getMainFormList() == null) {
				return "redirect:/main";
		}

		mainFormList_pkid = 0;
		for (MainFormList mainFormList : mainForm.getMainFormList()) {
			if (mainFormList.getSelectCheckboxs().length > 0) {
				mainFormList_pkid = mainFormList.getSelectCheckboxs()[0];
				demoService.delete(mainFormList_pkid);
			}
		}
		return "redirect:/main";
	}

/*

	@PostMapping(path = "upload")
	String upload(MainForm mainForm) {
		System.out.println("upload");
		MultipartFile mp_file = mainForm.getMainForm_file();
		String fileName = mp_file.getOriginalFilename();
		System.out.println("fileName:" + fileName);

		List<String> lines = new ArrayList<String>();
		String line = null;
		String[] line_split = null;
		DemoEntity temp_entity = new DemoEntity();

		try {
			InputStream stream = mp_file.getInputStream();
			Reader reader = new InputStreamReader(stream);
			BufferedReader buf= new BufferedReader(reader);
			while((line = buf.readLine()) != null) {
				System.out.println("controller line:" + line);

				if (line.equals("")) {
					continue;
				}

				line_split = line.split(",");
				for (int i = 0;i < line_split.length;i++) {
					line_split[i] = line_split[i].replace("\"","");
					line_split[i] = line_split[i].replace("'","");
				}

				System.out.println("line_split:" + line_split);
				temp_entity.setPkid(Integer.parseInt(line_split[0]));
				temp_entity.setName(line_split[1]);
				temp_entity.setWins(Integer.parseInt(line_split[2]));
				temp_entity.setBest(Integer.parseInt(line_split[3]));
				temp_entity.setSize(Float.parseFloat(line_split[4]));

				demoService.create(temp_entity);
//				demoService.create(line);
				System.out.println("create(line) finished");
			}
			line = buf.readLine();

		} catch (IOException e) {
			line = "Can't read contents.";
			lines.add(line);
			e.printStackTrace();
		}

		System.out.println("no catch");
		return "redirect:/";
	}
*/


/*
// http://javatec.blog105.fc2.com/blog-entry-22.html
	@PostMapping(path = "download")
//	@ResponseBody
	String download(MainForm mainForm,HttpServletResponse response) {
		System.out.println("download");

		Integer[] temp_Checkboxs;
		String temp_result = "";
		temp_Checkboxs = mainForm.getSelectCheckboxs();

		String str = "";

		for(int i =0;i < temp_Checkboxs.length;i++) {
			System.out.println("temp_Checkboxs[i]:" + String.valueOf(temp_Checkboxs[i]));
			temp_result = temp_result + "temp_Checkboxs[i]:" + temp_Checkboxs[i] + "<br>";

			temp_entity = demoService.findById(temp_Checkboxs[i]);

			System.out.println("temp_entity.pkid:" + temp_entity.getPkid());
			System.out.println("temp_entity.name:" + temp_entity.getName());
			System.out.println("");

			str = str +
			temp_entity.getPkid() + "," +
			"\"" + temp_entity.getName() + "\"" + "," +
			temp_entity.getWins() + "," +
			temp_entity.getBest() + "," +
			temp_entity.getSize() + "\r\n";

		}; // for(int i =0;i < temp_Checkboxs.length;i++) {

		try{

		byte[] buff = str.getBytes();

		response.setContentType("application/octet-stream; charset=UTF-8");
		response.setHeader("Content-Disposition","attachment; filename=list.csv");
		response.setContentLength(buff.length);

		 ServletOutputStream os = response.getOutputStream();
		 os.write(buff);
		 os.close();
		}
		catch(IOException e){
		 e.printStackTrace();
		}

		return "/";
//		return temp_result;
	}

	@PostMapping(path = "sub_win")
//	String edit(Integer list_pkid, EditForm editForm) {
	String sub_win(@RequestParam (name = "list_pkid", required = false)Integer list_pkid, EditForm editForm) {
		System.out.println("list_pkid:" + list_pkid);
		temp_entity = demoService.findById(list_pkid);
		BeanUtils.copyProperties(temp_entity, editForm);
		System.out.println("temp_entity:" + temp_entity);
		System.out.println("editForm:" + editForm);
		return "edit.html";
	}
*/
		System.out.println("no catch");
		return "redirect:/main";
	}
}
