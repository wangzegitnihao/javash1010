package com.atguigu.scw.webui.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.atguigu.common.bean.AppResponse;
import com.atguigu.scw.webui.vo.TOrder;


@FeignClient("SCW-ORDER")
public interface OrderServiceFeignClient {
	
	@PostMapping("/order/createOrder")
	public AppResponse<Object> createOrder(@RequestBody TOrder order);
	@GetMapping("/order/updateOrderStatus")
	public AppResponse<Object> updateOrderStatus(@RequestParam("ordernum")String ordernum,@RequestParam("status")String status);
}
