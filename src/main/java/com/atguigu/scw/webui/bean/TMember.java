package com.atguigu.scw.webui.bean;

import java.io.Serializable;

public class TMember implements Serializable{
    private Integer id;

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String authstatus;

    private String usertype;

    private String realname;

    private String cardnum;

    private String accttype;
    
    
    public TMember(Integer id, String loginacct, String userpswd, String username, String email, String authstatus,
			String usertype, String realname, String cardnum, String accttype) {
		super();
		this.id = id;
		this.loginacct = loginacct;
		this.userpswd = userpswd;
		this.username = username;
		this.email = email;
		this.authstatus = authstatus;
		this.usertype = usertype;
		this.realname = realname;
		this.cardnum = cardnum;
		this.accttype = accttype;
	}

	public TMember() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct == null ? null : loginacct.trim();
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd == null ? null : userpswd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(String authstatus) {
        this.authstatus = authstatus == null ? null : authstatus.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public String getAccttype() {
        return accttype;
    }

    public void setAccttype(String accttype) {
        this.accttype = accttype == null ? null : accttype.trim();
    }
}