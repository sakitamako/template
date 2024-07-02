package com.diworksdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.diworksdev.template.dao.BuyItemDAO;
import com.diworksdev.template.dao.LoginDAO;
import com.diworksdev.template.dto.BuyItemDTO;
import com. diworksdev.template.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
//実際の処理を持たない、ちょっと変わったクラス=implements
//Java7までは実装は持てず、メソッドのシグニチャのみの定義
//interfaceを使って型宣言を行うことができますが、メソッドの定義がないとプログラムは実行できないので、そこで使うのがimplements
public class LoginAction extends ActionSupport implements SessionAware {

	//フィールド変数
	//JSPから受け取る値、ここではnameとpassword を定義
	//※必ずJSPでの定義と同じ名前にする
	private String loginUserId;
	private String loginPassword;
	private String result;

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	private Map<String, Object> session;

	//メソッド名は「execute」
	//管理コマンド・メッセージをコマンド・サーバーに送信し、何らかの応答メッセージを待ちます
	public String execute() {

		//②LoginDAOとLoginDTOとbuyItemDAOのインスタンス化
		LoginDAO loginDAO = new LoginDAO();
		LoginDTO loginDTO = new LoginDTO();
		BuyItemDAO buyItemDAO = new BuyItemDAO();

		//メソッドの戻り値「ret」 String ret = ERROR; を定義し、初期値としてERRORを代入
		result = ERROR;

		//JSPから送られてきたnameとpasswordを引数として、
		//LoginDAOクラスのselectメソッドを呼び出す
		//その後、DAOで取得した結果をLoginDTOに代入する
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);

		//Map を使った場合には、put()で要素を記憶できる
		session.put("loginUser", loginDTO);

		//入力値からユーザー情報の検索を行います。
		//ログイン認証が成功した場合、次の画面で
		//「商品情報」が必要なため商品情報を取得します。
		//もしLoginDTOの中に入っているsessionの要素からloginUserとgetLoginFlg()を取得した場合success
		if (((LoginDTO) session.get("loginUser")).getLoginFlg()) {

			result = SUCCESS;

			//buyItemDTOファイルの17行目
			BuyItemDTO buyItemDTO = buyItemDAO.getBuyItemInfo();

			//Map を使った場合には、put()で要素を記憶できる
			//sessionの中に記憶した要素をそれぞれのDTOファイルから取得する
			session.put("login_user_id", loginDTO.getLoginId());
			session.put("id", buyItemDTO.getId());
			session.put("buyItem_name", buyItemDTO.getItemName());
			session.put("buyItem_price", buyItemDTO.getItemPrice());


			//戻り値
			//retに入った値を呼び出し元であるActionクラスに渡す
			return result;

		}

		//戻り値
		//retに入った値を呼び出し元であるActionクラスに渡す
		return result;
	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、loginUserIdフィールドの値をActionに渡す
	public String getLoginUserId() {
		return loginUserId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のloginUserIdフィールドに格納
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、loginpasswordフィールドの値をActionに渡す
	public String getLoginPassword() {
		return loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のloginpasswordフィールドに格納
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、sessionフィールドの値をActionに渡す
	public Map<String, Object> getSession() {
		return session;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のsessionフィールドに格納
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;

	}
}
