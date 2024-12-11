package com.controller;

import java.util.Scanner;

import com.service.Attendance;
import com.service.CrudStudent;
import com.service.LoginPage;

public class MainStudent {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		LoginPage l=new LoginPage();
		System.out.println("Enter Username and password:");
		String user=sc.next();
		//System.out.println("Enter Password:");
		int pass=sc.nextInt();
		l.compare(user, pass);
		int n=0;
		do {
		System.out.println("\nFor Show All The Data Enter 1:");
		System.out.println("For Login Tab Enter 2:");
		System.out.println("For Attendance Tab Enter 3:");
		System.out.println("For Student Tab Enter 4:");
		System.out.println("For Exiting The Program Enter 5:");
		n=sc.nextInt();
		
		
		switch (n) {
		
		case 1:
			l.showAllData();
			break;
			
		case 2:
			l.checkList();
			break;

		case 3:
			Attendance a=new Attendance();
			a.checkList();
			break;
			
		case 4:
			CrudStudent c=new CrudStudent();
			c.checkList();
			break;
			
		case 5:
			System.out.println("Exiting the program.");
			break;
			
		default:
			System.out.println("Enter a valid No!!!");
			break;
			
		}
		}while(n!=5);
		sc.close();
	}
}
