package com.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.daoimpl.InsuranceImpl;

public class InsuranceProject {

	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		try {
			System.out.println("***************************");
			System.out.println("\t1.Login\n\t2.Register");
			Scanner sc = new Scanner(System.in);
			System.out.println("***************************");
			System.out.println("Enter your choice");
			int ch = sc.nextInt();
			InsuranceImpl b = new InsuranceImpl();
			switch (ch) {
			case 1:
				b.login();
				break;
			case 2:
				b.addCustomer();
				break;
			default:
				System.out.println("Wrong Choice");
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Please enter Only Numbers");
			menu();
		}
	}
}
