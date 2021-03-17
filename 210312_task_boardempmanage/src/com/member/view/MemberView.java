package com.member.view;

import java.util.Scanner;

import com.member.controller.MemberController;

public class MemberView {

	public void mainMenu(MemberController mc) {
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("==== 회원 관리 메뉴 ====");
			
			System.out.println("1. 전체 회원 조회");
			System.out.println("2. 회원 아이디로 조회");
			System.out.println("3. 회원 이름으로 조회");
			System.out.println("4. 회원 가입");
			System.out.println("5. 회원 정보 수정"); // 주소 전화번호 이메일
			System.out.println("6. 회원 탈퇴"); 
			System.out.println("7. 메인메뉴로");
			
			int cho = sc.nextInt();
			
			switch(cho) {
			
				case 1:
					mc.searchAll();
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
					
				break;
					
			}
		}
	 }

	

}
