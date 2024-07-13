package com.diworksdev.template.dto;

//購入履歴機能トの作成

//DTOクラスは、DAOがDBから取得した値をActionへ戻す時、値を格納するのに利用されるファイル
public class MyPageDTO {

	//テーブルから取得するデータに対応したフィールド変数を宣言
	//このクラス・変数・変数名
	private String itemName;
	private String totalPrice;
	private String totalCount;
	private String payment;

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、itemNameフィールドの値をActionに渡す
	//get は値を取得、set は登録
	public String getItemName() {
		return this.itemName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO itemNameフィールドに格納
	public void setItemName(String itemName) {
		this.itemName = itemName;

	}

	//Actionクラスから呼び出され、totalPriceフィールドの値をActionに渡す
	public String getTotalPrice() {
		return this.totalPrice;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO totalPriceフィールドに格納
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;

	}

	//Actionクラスから呼び出され、totalCountフィールドの値をActionに渡す
	public String getTotalCount() {
		return this.totalCount;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO totalCountフィールドに格納
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;

	}

	//Actionクラスから呼び出され、paymentフィールドの値をActionに渡す
	public String getPayment() {
		return this.payment;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO paymentフィールドに格納
	public void setPayment(String payment) {
		this.payment = payment;

	}
}
