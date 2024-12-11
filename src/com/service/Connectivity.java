package com.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Connectivity {

	public static final String url = "jdbc:mysql://localhost:3306/studentattendance";
	public static final String userName = "root";
	public static final String pass = "bullet7343";
	public static PreparedStatement pstmt = null;
	public static Connection con = null;
	public static Scanner sc = new Scanner(System.in);

	public static Connection dbConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, pass);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

}
