package com.atguigu.scw.webui.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCreateInfoVo {
	//收货地址
	private String address;
	//是否需要发票0-不需要：1-需要
	private String invoice;
	//发票抬头
	private String invoictitle;
	//回报数量
	private Integer rtncount;
	//订单备注
	private String remark;
	
	
}
