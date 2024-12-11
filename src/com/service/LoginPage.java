package com.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.ResultSetMetaData;

public class LoginPage {

	public static PreparedStatement pstmt = null;
	public static Connection con = Connectivity.dbConnection();
	public static Scanner sc = new Scanner(System.in);

	private String s1;
	private int pass1;

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public int getPass1() {
		return pass1;
	}

	public void setPass1(int pass1) {
		this.pass1 = pass1;
	}

	public void createTable() {

		try {

			String sql = "insert into student2 values(?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, "admin");
			pstmt.setInt(2, 12345);

			pstmt.executeUpdate();
			System.out.println("Data Inserted successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void showData() {
		String sql2 = "select * from student2;";
		try {
			pstmt = con.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String s1 = rs.getString(1);
				int pass = rs.getInt(2);
				setS1(s1);
				setPass1(pass);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void  compare(String s, int pass) {
		if (getS1().equals(s) && getPass1()==pass) {
			System.out.println("Login Successfully!!!!");
		} else {
			System.out.println("invalid user name or password ");
		}
	}


	public static void readData() {
		String sql2 = "select * from student2;";
		try {
			pstmt = con.prepareStatement(sql2);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {

				String s1 = rs.getString(1);
				int pass = rs.getInt(2);
				System.out.println(s1 + " " + pass);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void updateUserPass() {
		System.out
				.println("Enter what you want to update user/pass:       --Do not enter any int value or other string");

		String column = sc.next();

		System.out.println("Enter old " + column);
		String oldValue = sc.next();
		sc.nextLine();
		System.out.println("Enter new " + column);
		String newValue = sc.nextLine();

		try {
			String sql = "update student2 set " + column + "='" + newValue + "' where " + column + "='" + oldValue+ "';";
			pstmt.executeUpdate(sql);
			System.out.println("Data updated successfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LoginPage() {
		Connectivity.dbConnection();
		showData();

	}

	public void checkList() {
		System.out.println("If you want to Update UserName or Password Enter 1:");
		System.out.println("If you want to read data Enter 2:");
		int n=sc.nextInt();
		switch (n) {
		case 1:
			LoginPage.updateUserPass();
			break;

		case 2:
			LoginPage.readData();
			break;

		default:
			System.out.println("Enter Correct Option!!!");
			break;
		}
	}
	
	public void showAllData() {
		try {
			DatabaseMetaData metaData = con.getMetaData();
			
            StringBuilder sb=new StringBuilder("select ");//select is sql query to start like 'selct' * from;
            
			String schema = "studentattendance"; 
            String tableName = "student"; 
            
            ResultSet rs = metaData.getColumns(null, schema, tableName, null);
            boolean firstColumn=true;
            
            while (rs.next()) {
               
                String columnName = rs.getString("COLUMN_NAME");
                System.out.print(columnName+"\t");
               
                if(firstColumn) {
                	
                	sb.append(columnName);
                	firstColumn=false;
                }
                else {
					sb.append(",").append(columnName);
				}
            }
            rs.close();
            
            sb.append(" from").append(" student");
            ResultSet data=pstmt.executeQuery(sb.toString());
            
            ResultSetMetaData metaDataResult = data.getMetaData();
            int columnCount = metaDataResult.getColumnCount();
            
            System.out.println();
            System.out.println();
            
            while (data.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = data.getString(i); // Get the value as String
                    
                    System.out.print(columnValue + "\t");
                    
                }
                System.out.println();
                System.out.println();
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
}
