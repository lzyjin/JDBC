package com.member.view;

import java.util.Scanner;

import com.member.controller.Controller;
import com.member.controller.MemberController;

public class View {
	
	private MemberController mc = new MemberController();

	public void mainMenu(Controller c) {
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("==== 프로그램 ====");
			
			System.out.println("1. 회원 관리");
			System.out.println("2. 게시판 관리");
			System.out.println("3. 프로그램 종료");
			
			int cho = sc.nextInt();
			
			switch(cho) {
			
				case 1: 
					mc.mainMenu();
				break;
			}
			
		}
	}

	
}
