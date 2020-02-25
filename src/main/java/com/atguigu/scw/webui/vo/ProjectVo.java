package com.atguigu.scw.webui.vo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectVo implements Serializable{
	
	private Integer id;
	private String headerImgurl;
	private String name;
	private String remark;
}
