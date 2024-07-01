package com.diworksdev.template.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.diworksdev.template.dto.LoginDTO;
import com.diworksdev.template.util.DBConnector;

//DAOクラスでは、Actionから送られてきた情報を使ってDBへ問い合わせを行うファイル
//問い合わせて取得した値をDTOクラスに格納するファイル
public class LoginDAO {

	//①クラス、メソッドの定義
	//LoginDTO型を最後に呼び出し元に渡すので、LoginDTO型を戻り値にしたメソッドを作る
	//Actionクラスの値を引数として受け取る
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword) {

		//②DBConnectorのインスタンス化
		//DBへの接続準備、DBと会話するためのコード、これでログインできる
		//Connectionは特定のデータベースとの接続
		DBConnector dbConnector = new DBConnector();

		//③getConnectionの呼び出し（DBと接続する）
		Connection connection = dbConnector.getConnection();

		//LoginDTOインスタンス化
		//DTOと会話するためのコード
		LoginDTO loginDTO = new LoginDTO();

		//④sql文を書く：値は ? を入れておく（どんな値でも使いまわしできるようにするため）
		//SELECT データを抽出する
		//＊ テーブルに含まれる項目全て
		//FROM 〇〇 〇〇という名前のテーブルからデータを選択する
		//WHERE ＜条件＞抽出条件を指定
		//login_user_transactionに入っているデータid=? pass=?に入る条件を満たしたデータがsqlに代入される
		String sql = "SELECT * FROM login_user_transaction where login_id=? AND login_pass=?";

		//try.catchはjavaの例外処理のための構文
		try {

			//tryの中にはエラーが発生しそうな処理を書く
			//⑤PreparedStatement（DBまで運んでくれる箱のイメージ）に代入
			//定義したSQL文の1番目の?にActionから送られたname、
			//2番目の?にActionから送られたpasswordがそれぞれ入る
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			//⑥sql文の?に入れる値をsetする
			preparedStatement.setString(1, loginUserId);
			preparedStatement.setString(2, loginPassword);

			//⑦executeQuery()/executeUpdate()で実行
			//（select文の場合はexectuteQuery()を使う）
			// select文のSQL文を実行するメソッドで戻り値はResultSet
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				loginDTO.setLoginId(resultSet.getString("login_id"));
			}

			//⑧結果の処理（select文で取得した値をDTOに格納）
			//select文でDBから取得した情報をString型に変換してDTOクラスに格納
			//LoginDTOクラスのsetName、setPassword（setter）を利用
			loginDTO.setLoginPassword(resultSet.getString("login_pass"));
			loginDTO.setUserName(resultSet.getString("user_name"));

			if (resultSet.getString(“login_id”) != null) {
				loginDTO.setLoginFlg(true);

			}

		//処理中にSQL関連のエラーが発生した際に実行する処理
		//tryの中でエラーが発生した場合、catchが受け取り
		//例外がスローされる原因となったエラーまたは動作の説明を返す
		} catch(Exception e) {
			e.printStackTrace();

		}

		//dtoに入った値を呼び出し元であるアクションクラスに渡す
		return loginDTO;

	}

}
