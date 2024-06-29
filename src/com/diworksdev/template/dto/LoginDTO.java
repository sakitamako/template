package com.diworksdev.template.dto;

//DTOクラスは、DAOがDBから取得した値をActionへ戻す時、値を格納するのに利用されるファイル
public class LoginDTO {

	//テーブルから取得するデータに対応したフィールド変数を宣言
	//このクラス・変数・変数名
	private String loginId;
	private String loginPassword;
	private String userName;

	//このクラス  true （真）か false （偽）の値のみを取れる論理データ型 変数名＝false
	private boolean loginFlg = false;

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、loginIdフィールドの値をActionに渡す
	//get は値を取得、set は登録
	public String getLoginId() {
		return loginId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO loginIdフィールドに格納
	public void setLoginId(String loginId) {
		this.loginId = loginId;

	}

	//Actionクラスから呼び出され、loginPasswordフィールドの値をActionに渡す
	public String getLoginPassword() {
		return loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO loginPasswordフィールドに格納
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;

	}

	//Actionクラスから呼び出され、nameフィールドの値をActionに渡す
	public String getUserName() {
		return userName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO nameフィールドに格納
	public void setUserName(String userName) {
		this.userName = userName;

	}

	//Actionクラスから呼び出され、loginFlgフィールドの値をActionに渡すa
	public boolean getLoginFlg() {
		return loginFlg;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO loginFlgフィールドに格納
	public void setLoginFlg(boolean loginFlg) {
		this.loginFlg = loginFlg;

	}

}
