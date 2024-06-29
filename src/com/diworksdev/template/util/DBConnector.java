package com.diworksdev.template.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

	//MySQL接続に必要な情報を設定
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost/ecsite";
	private static String user = "root";
	private static String password = "root";

	public Connection getConnection() {

		Connection con = null;

		try {

			Class.forName(driverName);

			//接続情報から自分のパソコンにインストールされているMySQLへ接続する準備が整う
			con = (Connection) DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch(SQLException e) {
			e.printStackTrace();

		}

		//Mysqlに接続できたか情報を渡す
		return con ;

	}

}
