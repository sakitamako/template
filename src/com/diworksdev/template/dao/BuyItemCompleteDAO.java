package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.diworksdev.template.util.DBConnector;
import com.diworksdev.template.util.DateUtil;

//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class BuyItemCompleteDAO {

	//このクラスのみ 変数 変数名 インスタンス化（コピーして代入）
	private DateUtil dateUtil = new DateUtil();

	//このクラスのみ 変数 変数名
	//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため）
	//SELECT データを抽出する
	//＊ テーブルに含まれる項目全て
	//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
	//WHERE ＜条件＞抽出条件を指定
	//login_user_transactionに入っているデータid, pass, name, dateに入る条件を満たしたデータがsqlに代入される
	private String sql = "INSERT INTO user_buy_item_transaction(item_transaction_id, total_price, total_count, user_master_id, pay, insert_date)VALUES(?, ?, ?, ?, ?, ?)";

	//全てのクラス  throws=例外を意図的に起こすことが出来る処理のこと。
	public void buyItemeInfo(String item_transaction_id, String user_master_id, String total_price, String total_count, String pay) throws SQLException {

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector dbConnector = new DBConnector();

		//③getConnectionの呼び出し（DBと接続する）
		Connection connection = dbConnector.getConnection();

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, total_price);
			preparedStatement.setString(3, total_count);
			preparedStatement.setString(4, user_master_id);
			preparedStatement.setString(5, pay);
			preparedStatement.setString(6, dateUtil.getDate());
			preparedStatement.execute();

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch(Exception e) {
			e.printStackTrace();

		//DB接続を終了する際、必ず書くメソッド
		//最後に実行されるものを指定するための構文
		//例外が発生しcatchされてもされなくても、共通してやってほしい処理や、やらなければいけない処理を書くところ。
		} finally {

			//⑨con.close()で接続を切る
			//データベースとの接続をクローズ
			//これをしないとデータベースを接続したまま作業が実行されてしまってメモリに負荷がかかる
			connection.close();

		}

	}

}
