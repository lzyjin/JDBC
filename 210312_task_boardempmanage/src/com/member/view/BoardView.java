package com.member.view;

import java.util.Scanner;

import com.member.controller.BoardController;

public class BoardView {
	
	private BoardController bc = new BoardController();

	public void mainMenu() {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("**** 게시판 서브메뉴 ****");
			System.out.println("1. 게시판 전체 검색");
			System.out.println("2. 게시물 등록");
			System.out.println("3. 작성자로 검색");
			System.out.println("4. 제목으로 검색");
			System.out.println("5. 게시물 수정"); // 제목, 내용을 idx로 수정 
			System.out.println("6. 게시물 삭제");
			System.out.println("7. 메인메뉴로");
			
			int cho = sc.nextInt();
			
			switch(cho) {
			
				case 1:
					bc.searchAll();
				break;
				case 2:
					
				break;
				case 3:
					
				break;
				case 4:
					
				break;
				case 5:
					
				break;
				case 6:
					
				break;
				case 7:
					System.out.println("메인메뉴로 돌아갑니다");
				return;
				default:
					System.out.println("메뉴를 제대로 입력해주세요.");
				break;
				
			}
		}
	}

}
