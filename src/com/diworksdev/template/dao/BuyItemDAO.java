package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.diworksdev.template.dto.BuyItemDTO;
import com.diworksdev.template.util.DBConnector;

//ログイン認証機能
//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class BuyItemDAO {

	//①クラス、メソッドの定義
	//DTO型を最後に呼び出し元に渡すので、DTO型を戻り値にしたメソッドを作る
	//Actionクラスの値を引数として受け取る
	public BuyItemDTO getBuyItemInfo() {

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector dbConnector = new DBConnector();

		//③getConnectionの呼び出し（DBと接続する）
		Connection connection = dbConnector.getConnection();

		//BuyItemDTOインスタンス化
		//DTOと会話するためのコード
		BuyItemDTO buyItemDTO = new BuyItemDTO();

		//④sql文を書く
		//SELECT item_info_transactionのデータを抽出する
		//item_stock追加したら、、、？
		String sql = "SELECT id, item_name, item_price FROM item_info_transaction";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			ResultSet resultSet = preparedStatement.executeQuery();

			//もしsqlから取得したデータが存在していれば下に一行ずらすこと
			//データが存在していれば戻り値を true で返す。存在しなければ falseで返す
			if (resultSet.next()) {

				//もしresultsetに入っている値が存在していればDTOに格納する
				buyItemDTO.setId(resultSet.getInt("id"));
				buyItemDTO.setItemName(resultSet.getString("item_name"));
				buyItemDTO.setItemPrice(resultSet.getString("item_price"));
				//buyItemDTO.setItemPrice(resultSet.getString("item_stock"));追加したら、、、？
			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch(Exception e) {
			e.printStackTrace();

		}

		//dtoに入った値を呼び出し元であるアクションクラスに渡す
		return buyItemDTO;

	}
}
