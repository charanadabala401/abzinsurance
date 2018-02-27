package com.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.daoimpl.Abcinsurance;

public class InsuranceProject {

	public static void main(String[] args) {
	
		menu();
		
	
	
	}
	public static void menu() {
		System.out.println("\t\t\t*******WELCOME TO ABC INSURANCE ******");
		try {
		System.out.println("\t\t\t\t1.Login \n\t\t\t\t2.registration\n\t\t\t\t3.Exit");
		System.out.println("\t\t\t*****************************");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		int ch = sc.nextInt();

		Abcinsurance s = new Abcinsurance();
		if (ch == 1) {
			s.login();
		} else if (ch == 2) {
			s.registration();
		} 
		else if(ch==3)
		{
			System.out.println("Thank You");
			System.exit(0);
			
		}
		else {
			System.out.println("Wrong choice");
			menu();

		}
		}
		catch(InputMismatchException e) {
			System.out.println("Enter only digits");
			menu();
		}
		

	}

}
