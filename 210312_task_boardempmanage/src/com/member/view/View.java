package com.member.view;

import java.util.Scanner;

import com.member.controller.BoardController;
import com.member.controller.Controller;

public class View {
	
	private BoardController bc = new BoardController();

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
					System.out.println("공사중...");
				break;
				
				case 2: 
					bc.mainMenu();
				break;
			}
			
		}
	}

	
}
