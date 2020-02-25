package com.atguigu.scw.webui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.atguigu.scw.webui.bean.TMember;

@Controller
public class HelloWorldController {

	@GetMapping("hehehe")
	public String hehehe(Model model,HttpSession session) {
		model.addAttribute("reqKey", "reqVal");
		
		session.setAttribute("sessionkey", "sessionVal");
		
		ServletContext servletContext = session.getServletContext();
		List<TMember> list = new ArrayList<TMember>();
		list.add(new TMember(1, "吴彩霞", "3213", "大爷", "3123213@qq.com", "dasda", null, "4234234", "dewdwe", "dewdew"));
		list.add(new TMember(2, "秦燕", "3213", "二爷", "3123213@qq.com", "dasda", null, "4234234", "dewdwe", "dewdew"));
		list.add(new TMember(3, "杨小凡", "3213", "三爷", "3123213@qq.com", "dasda", null, "4234234", "dewdwe", "dewdew"));
		list.add(new TMember(4, "张玉兰", "3213", "四爷", "3123213@qq.com", "dasda", null, "4234234", "dewdwe", "dewdew"));
		list.add(new TMember(5, "女友", "3213", "五爷", null, "dasda", null, "4234234", "dewdwe", "dewdew"));
		session.setAttribute("merber",list );
		
		
		return  "llllllleee";
	}
	@GetMapping("/text")
	public String text() {
		System.out.println("======");
		return "llllllleee";
	}
}
