package com.atguigu.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.common.bean.AppResponse;
import com.atguigu.scw.webui.vo.ProjectDetailsVo;
import com.atguigu.scw.webui.vo.ProjectVo;
import com.atguigu.scw.webui.vo.TReturn;

@FeignClient(value="SCW-PROJECT")
public interface ProjectServiceFeignClient {
	
	@GetMapping("/project/recommend/index")
	public AppResponse<List<ProjectVo>> index();
	
	@GetMapping("/project/info/detail")
	public AppResponse<ProjectDetailsVo> detail(@RequestParam("id") Integer id);
	
	//public AppResponse<TReturn> getReturn(Integer rtnid);
	//查询回报的远程方法
	@GetMapping("/project/return/info")
	public AppResponse<TReturn> support(@RequestParam("rtnid")Integer rtnid);
}
