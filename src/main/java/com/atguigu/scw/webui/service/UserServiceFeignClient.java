package com.atguigu.scw.webui.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.common.bean.AppResponse;
import com.atguigu.scw.webui.service.imp.UserServiceFeignClientExceptionHandler;
import com.atguigu.scw.webui.vo.TMemberAddress;

@FeignClient(value="SCW-USER",fallback=UserServiceFeignClientExceptionHandler.class) //声明当前接口是一个feign客户端
public interface UserServiceFeignClient {
	 
	
	@GetMapping("/user/doLogin")
	public AppResponse<Object> doLogin(@RequestParam("loginacct")String loginacct,@RequestParam("userpswd")String userpswd);
	
	@GetMapping("/user/getAllAddress")
	public AppResponse<List<TMemberAddress> > getAllAddress(@RequestParam("accessToken")String accessToken);
	
	@GetMapping("/user/getMemberid")
	public AppResponse<Integer>  getMemberid(@RequestParam("accessToken")String accessToken);
}
