package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CrudStudent {

	public static PreparedStatement pstmt = null;
	public static Connection con = Connectivity.dbConnection();
	public static Scanner sc = new Scanner(System.in);

	public static void addStudent() {
		System.out.println("How many student you want to add:");
		int n = sc.nextInt();
		if (n == 0) {
			System.out.println("Enter valid No!!!");
			return;
		}
		for (int i = 1; i <= n; i++) {
			System.out.println("Enter student id:");
			int id = sc.nextInt();

			sc.nextLine();

			System.out.println("Enter student name:");
			String name = sc.nextLine();

			try {
				String add = "INSERT INTO student (s_id,s_name) VALUES (?,?);";
				pstmt = con.prepareStatement(add);
				pstmt.setInt(1, id);
				pstmt.setString(2, name);
				pstmt.executeUpdate();
				System.out.println("Data Inserted successfully");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
//	public static void readPerticularStudent() {
//		System.out.println("Enter student id:");
//		int a=sc.nextInt();
//		try {
//			String sql = "select * from student where s_id=?;";
//			pstmt.setInt(1, a);
//			pstmt = con.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			while (rs.next()) {
//				int id = rs.getInt(1);
//				String name = rs.getString(2);
//				System.out.println(id + " " + name);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public static void readStudent() {

		try {
			String sql = "select * from student;";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(id + " " + name);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateStudent() {
		System.out.println("Enter student ID:");
		int id = sc.nextInt();
		sc.nextLine();
		if (id <= 0) {
			System.out.println("This type of id is not present");
			return;
		}
		System.out.println("Enter new name of student:");
		String newValue = sc.nextLine();

		try {
			String sql = "update student set s_name=? where s_id=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, newValue);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Data Updated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void deleteStudent() {
		System.out.println("Enter student ID:");
		int id = sc.nextInt();
		if (id <= 0) {
			System.out.println("This type of id is not present");
			return;
		}

		try {
			String sql = "delete from student where s_id=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Data deleted Successfully...");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public CrudStudent() {
		Connectivity.dbConnection();

	}

	public void checkList() {
		System.out.println("If you want to Add Student Enter 1:");
		System.out.println("If you want to Update Student Enter 2:");
		System.out.println("If you want to delete Student Enter 3:");
		System.out.println("If you want to Read Student Enter 4:");
		int n=sc.nextInt();
		switch (n) {
		case 1:
			CrudStudent.addStudent();
			break;

		case 2:
			CrudStudent.updateStudent();
			break;

		case 3:
			CrudStudent.deleteStudent();
			break;

		case 4:
			CrudStudent.readStudent();
			break;

		default:
			System.out.println("Enter Correct Option!!!");
			break;
		}
	}
}
