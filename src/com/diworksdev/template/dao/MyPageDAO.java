package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.diworksdev.template.dto.MyPageDTO;
import com.diworksdev.template.util.DBConnector;

//購入履歴機能トの作成

//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class MyPageDAO {

	//①クラス、メソッドの定義
	//DTO型を最後に呼び出し元に渡すので、DTO型を戻り値にしたメソッドを作る
	//Actionクラスの値を引数として受け取る,throws=例外を意図的に起こすことが出来る処理のこと。
	public MyPageDTO getMyPageUserInfo(String item_transaction_id, String user_master_id) throws SQLException {

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector dbConnector = new DBConnector();

		//③getConnectionの呼び出し（DBと接続する）
		Connection connection = dbConnector.getConnection();

		//LoginDTOインスタンス化
		//DTOと会話するためのコード
		MyPageDTO myPageDTO = new MyPageDTO();

		//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため）
		//SELECT データを抽出する
		//＊ テーブルに含まれる項目全て
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		//WHERE ＜条件＞抽出条件を指定
		//データベースに入ってる条件を満たしたデータがsqlに代入される
		//JOINの左側のテーブルが結合条件に一致しなくてもレコードをは返します
		//ORDER BY=降順に並べ替える
		String sql = "SELECT iit.item_name, ubit.total_price, ubit.total_count, "
				+ "ubit.pay FROM user_buy_item_transaction ubit LEFT JOIN item_info_transaction iit ON "
				+ "ubit.item_transaction_id = iit.id WHERE "
				+ "ubit.item_transaction_id = ? AND ubit.user_master_id = ? ORDER BY ubit.insert_date DESC";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			//⑥sql文の?に入れる値をsetする
			ResultSet resultSet = preparedStatement.executeQuery();

			//下に1行ずらすこと
			//データが存在していれば戻り値を true で返す。存在しなければ falseで返す
			if (resultSet.next()) {

				//もしresultsetに入っている値が存在していればDTOに格納する
				myPageDTO.setItemName(resultSet.getString("item_name"));
				myPageDTO.setTotalPrice(resultSet.getString("total_price"));
				myPageDTO.setTotalCount(resultSet.getString("total_count"));
				myPageDTO.setPayment(resultSet.getString("pay"));
			}

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

		//DTOファイルに格納しているデータをセットする
		return myPageDTO;

	}

	//①クラス、メソッドの定義
	//全てのクラス 整数数値 変数名 引数
	//Actionクラスの値を引数として受け取る,throws=例外を意図的に起こすことが出来る処理のこと。
	public int buyItemHistoryDelete(String item_transaction_id, String user_master_id) throws SQLException {

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector dbConnector = new DBConnector();

		//③getConnectionの呼び出し（DBと接続する）
		Connection connection = dbConnector.getConnection();

		//DELETE=削除
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		//WHERE ＜条件＞抽出条件を指定
		//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため）
		String sql = "DELETE FROM user_buy_item_transaction WHERE item_transaction_id = ? AND user_master_id = ?";

		//tryの中にはエラーが発生しそうな処理を書く
		//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
		PreparedStatement preparedStatement;

		int result = 0;

		//tryの中にはエラーが発生しそうな処理を書く
		try {

			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			preparedStatement.setString(1, item_transaction_id);
			preparedStatement.setString(2, user_master_id);

			//⑥sql文の?に入れる値をsetする
			result = preparedStatement.executeUpdate();

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch(SQLException e) {
			e.printStackTrace();

		//DB接続を終了する際、必ず書くメソッド
		//最後に実行されるものを指定するための構文
		//例外が発生しcatchされてもされなくても、共通してやってほしい処理や、やらなければいけない処理を書くところ
		} finally {

			//⑨con.close()で接続を切る
			//データベースとの接続をクローズ
			//これをしないとデータベースを接続したまま作業が実行されてしまってメモリに負荷がかかる
			connection.close();

		}

		//DTOファイルに格納しているデータをセットする
		return result;

	}

}


