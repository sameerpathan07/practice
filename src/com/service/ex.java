package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ex {

	public static final String DATE_COLUMN = "attendance_date";
	public static final String ATTENDANCE_COLUMN = "attendance";

	private Connection con;
	private Scanner sc;

	public ex() throws SQLException {
		con = Connectivity.dbConnection(); // Assuming Connectivity class provides connection
		sc = new Scanner(System.in);
	}

	public void addAttendance() throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement("SELECT * FROM student")) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(id + " " + name);

				String attendance = getValidAttendance();

				try (PreparedStatement updateStmt = con
						.prepareStatement("UPDATE student SET " + ATTENDANCE_COLUMN + " = ?" + " WHERE id = ?")) {
					updateStmt.setString(1, attendance);
					updateStmt.setInt(2, id);
					updateStmt.executeUpdate();
				}
			}
		}
	}

	private String getValidAttendance() {
		String attendance;
		do {
			System.out.println("Enter attendance (P or F):");
			attendance = sc.nextLine().toUpperCase();
		} while (!attendance.equals("P") && !attendance.equals("F"));
		return attendance;
	}

	public static void main(String[] args) throws SQLException {
		ex attendance = new ex();
		attendance.addAttendance();
	}
}