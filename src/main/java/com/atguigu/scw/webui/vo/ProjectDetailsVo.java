package com.atguigu.scw.webui.vo;

import java.io.Serializable;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectDetailsVo implements Serializable{
	
	private Integer id;

    private String name;

    private String remark;

    private Long money;

    private Integer day;

    private String status;

    private String deploydate;

    private Long supportmoney;

    private Integer supporter;

    private Integer completion;

    private Integer memberid;

    private String createdate;

    private Integer follower;
    
    //================项目发起人数据=========================
    
    private String selfintroduction;

    private String detailselfintroduction;

    private String telphone;

    private String hotline;

    //================汇报信息=========================
    private List<TReturn> rtns;
    
    //===============项目头图=========================
    private String headerImgurl;
    //===============项目详情图=========================
    private List<String> detailsImgurls;

}
