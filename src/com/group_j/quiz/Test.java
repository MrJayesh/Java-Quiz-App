package com.group_j.quiz;
import java.util.Scanner;

public class Test {

	
	
	public static void main(String[] args) { //Jayesh
		
		Questions questions = new Questions();
		Admin admin = new Admin();
		new DataBase();
		System.out.println("Please Select correct Input");
		System.out.println("1. Take Test");
		System.out.println("2. Admin Login");
		
		Scanner sc = new Scanner(System.in);
		int input=sc.nextInt();
		
		switch (input) {
		case 1:
			questions.takeTest();
			break;
			
		case 2:
			admin.adminlogin();
			break;
		default:
			System.out.println("Incorrect Input");
			break;
		}
		sc.close();
		
	}

}
