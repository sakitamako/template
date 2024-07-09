package com.diworksdev.template.action;

import com.opensymphony.xwork2.ActionSupport;

//ログイン認証機能

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル
//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
public class HomeAction extends ActionSupport {

	//「excecute」メソッドを実行した後は"success"文字列を返す
	public String execute() {
		return SUCCESS;

	}
}
