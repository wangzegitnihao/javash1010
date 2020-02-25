package com.atguigu.scw.webui.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.atguigu.common.bean.AppResponse;
import com.atguigu.scw.webui.service.UserServiceFeignClient;
import com.atguigu.scw.webui.vo.TMemberAddress;

@Service
public class UserServiceFeignClientExceptionHandler implements UserServiceFeignClient{

	@Override
	public AppResponse<Object> doLogin(String loginacct, String userpswd) {

		return AppResponse.feli("20000", "服务器异常，请重试");
	}

	@Override
	public AppResponse<List<TMemberAddress>> getAllAddress(String accessToken) {
		// TODO Auto-generated method stub
		return AppResponse.feli("20000", "服务器异常，请重试");
	}

	@Override
	public AppResponse<Integer> getMemberid(String accessToken) {
		// TODO Auto-generated method stub
		return AppResponse.feli("20000", "服务器异常，请重试");
	}

		
}
