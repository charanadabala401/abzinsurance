package com.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static Connection getMySqlConnection() {
		Connection con = null;
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/insurance";
		String user = "root";
		String password = "root";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				// System.out.println("connected");
			} else {
				System.out.println("connection failled");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	public static Connection getOracleConnection() {
		Connection con = null;
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			if (con != null) {
				System.out.println("connected");
			} else {
				System.out.println("connection failled");
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
