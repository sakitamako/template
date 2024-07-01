package com.diworksdev.template.dto;

public class BuyItemDTO {

	//テーブルから取得するデータに対応したフィールド変数を宣言
	//このクラス・変数・変数名
	private int id;
	private String itemName;
	private String itemPrice;

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、itemNameフィールドの値をActionに渡す
	//get は値を取得、set は登録
	public String getItemName() {
		return itemName;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO itemNameフィールドに格納
	public void setItemName(String itemName) {
		this.itemName = itemName;

	}

	//Actionクラスから呼び出され、itemPriceフィールドの値をActionに渡す
	public String getItemPrice() {
		return itemPrice;

	}

	//フィールド変数に対応したgetterとsetterを定義itemPriceフィールドに格納
	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;

	}

	//Actionクラスから呼び出され、idフィールドの値をActionに渡す
	public int getId() {
		return id;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のDTO idフィールドに格納
	public void setId(int id) {
		this.id = id;

	}
}
