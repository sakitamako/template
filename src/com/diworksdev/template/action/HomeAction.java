package com.diworksdev.template.action;

import com.opensymphony.xwork2.ActionSupport;

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル
public class HomeAction extends ActionSupport {

	public String execute() {
		return SUCCESS;

	}
}
