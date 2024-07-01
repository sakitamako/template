package com.diworksdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.template.dao.BuyItemDAO;
import com.diworksdev.template.dao.LoginDAO;
import com.diworksdev.template.dto.BuyItemDTO;
import com. diworksdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {

	private String loginUserId;
	private String loginPassword;
	private String result;
	private Map<String, Object> session;

	public String execute() {

		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();
		BuyItemDAO buyItemDAO = new BuyItemDAO();

		result = ERROR;
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
		session.put("loginUser", loginDTO);

		//入力値からユーザー情報の検索を行います。
		//ログイン認証が成功した場合、次の画面で
		//「商品情報」が必要なため商品情報を取得します。
		if (((LoginDTO) session.get("loginUser")).getLoginFlg()) {

			result = SUCCESS;

			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();
			session.put("login_user_id",loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());

			return result;

		}

		return result;
	}

	public String getLoginUserId() {
		return loginUserId;

	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;

	}

	public String getLoginPassword() {
		return loginPassword;

	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;

	}

	public Map<String, Object> getSession() {
		return session;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}
}
