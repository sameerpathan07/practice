package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Attendance {

	public static PreparedStatement pstmt = null;
	public static Connection con = Connectivity.dbConnection();
	public static Scanner sc = new Scanner(System.in);
	private String d;

	public void addColumn() {
		System.out.println("Enter today day:");
		sc.nextLine();
		String d = sc.nextLine();
		this.d = d;
		if(d.equals("0")) {
			System.out.println("Invalid input!!!");
			return;
		}

		try {
			String sql = "alter table student add column " + d + " text;";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addAtten() {
		addColumn();
		try {
			String sql = "select * from student;";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(id + " " + name);
				// sc.nextLine();
				String s1 = sc.next();

				if (s1.equalsIgnoreCase("P") || s1.equalsIgnoreCase("A")) {
					String add = "UPDATE student SET " + d + " = ? WHERE s_id = ?;";
					PreparedStatement qstmt = con.prepareStatement(add);
					qstmt.setString(1, s1.toUpperCase());
					qstmt.setInt(2, id);
					qstmt.executeUpdate();

				}

				else {
					System.out.println("Enter correct word!!!");
				}
			}
			System.out.println("Attendance Updated Successfully!!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAtten() {
		System.out.println("Enter the day:");
		sc.nextLine();
		String d = sc.nextLine();
		System.out.println("Enter student ID:");
		int id = sc.nextInt();
		if (id <= 0) {
			System.out.println("This type of id is not present");
			return;
		}

		System.out.println("Enter present or absent:");
		String a = sc.next();

		try {
			String sql = "update student set " + d + "=? where s_id=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
			System.out.println("Data Updated successfully");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteColumn() {

		System.out.println("Enter you want to delete the day:");
		sc.nextLine();
		String d = sc.nextLine();

		try {
			String sql = "ALTER TABLE student DROP COLUMN " + d + ";";
			pstmt = con.prepareStatement(sql);
			// pstmt.setString(1, d);
			pstmt.executeUpdate();
			System.out.println("Data deleted Successfully...");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void readStudent() {

		System.out.println("Enter specific column name which you want:");
		sc.nextLine();
		String s1 = sc.next();

		System.out.println("Enter specific data which is student present or not:");
		String s2 = sc.next();

		try {
			String sql = " select * from student where " + s1 + "=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, s2);
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

	public Attendance() {
		Connectivity.dbConnection();

	}

	public void checkList() {
		System.out.println("If you want to Mark Attendance of Student Enter 1:");
		System.out.println("If you want to Update Attendance of Student Enter 2:");
		System.out.println("If you want to delete Attendance of Student Enter 3:");
		System.out.println("If you want to Read Attendance of Student Enter 4:");
		int n=sc.nextInt();
		switch (n) {
		case 1:
			addAtten();
			break;

		case 2:
			updateAtten();
			break;

		case 3:
			deleteColumn();
			break;

		case 4:
			readStudent();
			break;

		default:
			System.out.println("Enter Correct Option!!!");
			break;
		}
	}
	
}
