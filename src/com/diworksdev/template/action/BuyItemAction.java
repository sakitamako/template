package com.diworksdev.template.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

//商品購入機能

//Actionクラスでは、画面から送られてきたリクエストを取得する
//内部処理に応じてDAOやDTOクラスを呼び出し、最終的に次のJSPへ値を返すファイル

//struts2が持つActionSupportというクラスを継承
//（Actionクラスは基本的にこのクラスを継承）
//LoginAciton（子クラス） extends（継承） ActionSupport（親クラス）
//すでにあるクラスとにたクラスを作る場合、元のクラスに必要な機能だけを追加する形で、新しいクラスを作ることを継承
//実際の処理を持たない、ちょっと変わったクラス=implements
//interfaceを使って型宣言を行うことができますが、メソッドの定義がないとプログラムは実行できないので、そこで使うのがimplements
/*Actionクラスにて、implements SessionAware を宣言（ActionSupport.SessionAware=インターフェース）
実装メソッドである setSession(Map session)にて、ActionのフィールドへHttpSessionのオブジェクトを格納する処理を実装する。this.session = session; でほぼ十分。
上記の手順で実装したフィールドを用意する
これにより、このActionクラスのsessionフィールドへ、Struts2が自動的にHttpSessionの内容をMapの型で格納します。*/
public class BuyItemAction extends ActionSupport implements SessionAware {

	//フィールド変数
	//JSPから受け取る値
	//※必ずJSPでの定義と同じ名前にする
	private int stock;
	private String pay;

	//Map<String, Object>=キーを値にマッピングするオブジェクト。
	//マップには、同一のキーを複数登録できない。各キーは1つの値にしかマッピングできません。
    //このインタフェースは、インタフェースというよりむしろ完全に抽象クラスであったDictionaryクラスに代わるものです
	//全てのクラス 変数 変数名
	public Map<String, Object> session;

	//このクラスのみ 変数 変数名
	private String result;

	//全てのクラス 変数 変数名(struts)
	public String execute() {

		//resultにサクセス代入
		result = SUCCESS;

		//sessionの中に入っているデータを記憶・保存する
		session.put("stock", stock);

		//Integerクラスは、プリミティブ型intの値をオブジェクトにラップします。Integer型のオブジェクトには、型がintの単一フィールドが含まれます。
		//さらにこのクラスは、intをStringに、Stringをintに変換する各種メソッドや、intの処理時に役立つ定数およびメソッドも提供します。
		//文字列の引数を解釈し、指定された基数 （数学的記数法の底）の整数値を返します
		//sessionの中のデータを取得してテキストで表す
		int intStock = Integer.parseInt(session.get("stock").toString());
		int intPrice = Integer.parseInt(session.get("buyItem_price").toString());

		//セッションの中にデータを記憶する
		session.put("buyItem_price", intStock * intPrice);

		//初期値
		String payment;

		//int型などのプリミティブ型で２つの値が等しいか比較する場合は”==”演算子で比較しますがString型などの参照型の場合はequalsメソッドで比較する
		//もしpayが1と等しい場合
		if (pay.equals("1")) {

			//paymentに現金払いを代入
			payment = "現金払い";

			//sessionに記憶
			session.put("pay", payment);

		} else {

			payment = "クレジットカード";

			//sessionに記憶
			session.put("pay", payment);
		}

		//戻り値
		//retに入った値を呼び出し元であるActionクラスに渡す
		return result;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、stockフィールドの値をActionに渡す
	public int getStock() {
		return stock;
	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のstockフィールドに格納
	public void setStock(int stock) {
		this.stock = stock;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//Actionクラスから呼び出され、payフィールドの値をActionに渡す
	public String getPay() {
		return pay;

	}

	//フィールド変数に対応したgetterとsetterを定義
	//DAOクラスから呼び出され、引数として受け取ったテーブルの値を自身のpayフィールドに格納
	public void setPay(String pay) {
		this.pay = pay;

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
