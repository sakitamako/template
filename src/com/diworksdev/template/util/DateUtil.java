package com.diworksdev.template.util;

import java.text.SimpleDateFormat;
import java.util.Date;

//全てのクラス 変数
public class DateUtil {

	//全てのクラス 変数 変数名
	public String getDate() {

		//インスタンス化（コピーして代入）
		//年、月、日、時間、分、秒の値を受け取るか返すクラスDateのすべてのメソッドにおいて、次の表示が使用されます
		Date date = new Date();

		//SimpleDateFormatは、日付のフォーマットと解析を、ロケールを考慮して行うための具象クラスです。
		//フォーマット(日付→テキスト)、解析(テキスト→日付)および正規化を行うことができます。
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		//処理結果を渡す
		return simpleDateFormat.format(date);

	}
}
