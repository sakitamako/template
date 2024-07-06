package com.diworksdev.template.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.template.dao.MyPageDAO;
import com.diworksdev.template.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware {

	public Map<String, Object> session;
	public String deleteFlg;
	private String result;
	public String execute() throws SQLException {
	MyPageDAO myPageDAO = new MyPageDAO();
	MyPage DTOmyPageDTO = new MyPageDTO();

	}

}
