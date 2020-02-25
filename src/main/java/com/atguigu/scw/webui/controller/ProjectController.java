package com.atguigu.scw.webui.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.common.bean.AppResponse;
import com.atguigu.common.consts.ScwAppconsts;
import com.atguigu.scw.webui.service.ProjectServiceFeignClient;
import com.atguigu.scw.webui.vo.ProjectDetailsVo;
import com.atguigu.scw.webui.vo.TReturn;

import lombok.extern.slf4j.Slf4j;




@RequestMapping("/project")
@Controller
@Slf4j
public class ProjectController {
	@Autowired
	ProjectServiceFeignClient projectServiceFeignClient;
	//准备下单页面的数据
	@GetMapping("/support")
	public String toPayStep1(Integer projectid,Integer rtnid,HttpSession session) {
		//1、从查询项目详情方法中session域中查询项目名称、发起人信息
//		ProjectDetailsVo ProjectDetailsVo = (ProjectDetailsVo)session.getAttribute(ScwAppconsts.PROJECT_DETAILS_KEY);
//		String ProjectName = ProjectDetailsVo.getName();
//		String selfintroduction = ProjectDetailsVo.getSelfintroduction();
		
		//2、查询回报信息:遍历ProjectDetailsVo rtns集合、查询数据库
		AppResponse<TReturn> response = projectServiceFeignClient.support(rtnid);
		TReturn tReturn = response.getData();
		log.warn("查询到id={}的回报的信息为：{}",rtnid,tReturn);
		//3、将数据存到session域中
		session.setAttribute("rtn", tReturn);
		//4、转发paystep1页面显示数据
		
		return "order/pay-step-1";
	}
	
	
	@GetMapping("/project.html")
	public String getProjectInfo(Integer id,HttpSession session,Model model) {
		//调用projectFeignClient远程调用 project项目查询id对应的项目详情
		AppResponse<ProjectDetailsVo> response = projectServiceFeignClient.detail(id);
		//将项目详情对象存到request域中
		if (!"00000".equals(response.getCode())) {
			return "error/502";//转发到错误页面提示
		}
		model.addAttribute(ScwAppconsts.PROJECT_DETAILS_KEY, response.getData());
		session.setAttribute(ScwAppconsts.PROJECT_DETAILS_KEY, response.getData());
		//转发到project详情显示页面
		
		return "project/project";
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
