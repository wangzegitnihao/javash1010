package com.atguigu.scw.webui.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@ApiModel("登陆成功的响应类")
public class UserRespVo implements Serializable{
	@ApiModelProperty("访问令牌,以后的请求必须携带此令牌代表登录状态")
	private String accessTokan;
	@ApiModelProperty("登陆账号")
	private String loginacct;
	@ApiModelProperty("用户名")
	private String username;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("认证状态：0-未认证 1-认证")
	private String authstatus;
	@ApiModelProperty("账户类型：0- 个人")
	private String usertype;
	@ApiModelProperty("真是姓名")
	private String realname;
	@ApiModelProperty("身份证号码")
	private String cardnum;
	@ApiModelProperty("账户类型")
	private String accttype;
}
