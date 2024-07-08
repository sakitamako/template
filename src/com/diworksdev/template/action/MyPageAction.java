package com.diworksdev.template.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.template.dao.MyPageDAO;
import com.diworksdev.template.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
//実際の処理を持たない、ちょっと変わったクラス=implements
//Java7までは実装は持てず、メソッドのシグニチャのみの定義
public class MyPageAction extends ActionSupport implements SessionAware {

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//フィールド変数
	//JSPから受け取る値
	//※必ずJSPでの定義と同じ名前にする
	//全てのクラス 変数 変数名
	public String deleteFlg;

	//このクラスのみ 変数 変数名
	private String result;

	//全てのクラス 変数 変数名(struts) throws=例外を意図的に起こすことが出来る処理のこと。
	public String execute() throws SQLException {

		//②DTOとDAOのインスタンス化（コピーして値を代入）
		MyPageDAO myPageDAO = new MyPageDAO();
		MyPageDTO myPageDTO = new MyPageDTO();


		// 商品履歴を削除しない場合
		if (deleteFlg == null) {

			//sessionの中に入っているidを取得してテキストで表示する
			String item_transaction_id = session.get("id").toString();
			String user_master_id = session.get("login_user_id").toString();

			//JSPから送られてきたidを引数として、
			//DAOクラスのMyPageUserInfoメソッドを呼び出す
			//その後、DAOで取得した結果をDTOに代入する
			myPageDTO = myPageDAO.getMyPageUserInfo(item_transaction_id, user_master_id);

			//sessionに入っている値を記憶 （DTOに入っているそれぞれのデータを）
			session.put("buyItem_name", myPageDTO.getItemName());
			session.put("total_price", myPageDTO.getTotalPrice());
			session.put("total_count", myPageDTO.getTotalCount());
			session.put("total_payment", myPageDTO.getPayment());
			session.put("message", "");

		// 商品履歴を削除する場合
		} else if(deleteFlg.equals("1")) {
			delete();

		}

		//33行目のresult
		result = SUCCESS;

		//戻り値
		//resultに入った値を呼び出し元であるActionクラスに渡す
		return result;

	}

	//全てのクラス 変数 変数名(struts) throws=例外を意図的に起こすことが出来る処理のこと。
	public void delete() throws SQLException {

		//②DTOとDAOのインスタンス化（コピーして値を代入）
		MyPageDAO myPageDAO = new MyPageDAO();

		//sessionの中に入っているidを取得してテキストで表示する
		String item_transaction_id = session.get("id").toString();
		String user_master_id = session.get("login_user_id").toString();

		//int=9 桁または 10 桁の精度で、-2,147,483,647 から 2,147,483,647 の範囲の整数を格納
		//JSPから送られてきたidを引数として、
		//DAOクラスのbuyItemHistoryDeleteメソッドを呼び出す
		//その後、DAOで取得した結果をDTOに代入する
		int res = myPageDAO.buyItemHistoryDelete( item_transaction_id, user_master_id);

		//もしDAOで取得した値が０より大きい場合
		if (res > 0) {

			//sessionに記憶している下記を表示する
			session.put("message", "商品情報を正しく削除しました。");

		//そうでない場合、もしDAOで取得した値と０が等しい場合
		} else if(res == 0) {

		}

		//sessionに記憶している下記を表示する
		session.put("message", "商品情報の削除に失敗しました。");

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、deleteFlgフィールドの値をActionに渡す
	public String getDeleteFlg() {
		return deleteFlg;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のdeleteFlgフィールドに格納
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のloginSessionMapフィールドに格納
	@Override
	public void setSession(Map<String, Object> loginSessionMap) {
		this.session = loginSessionMap;
	}

}
