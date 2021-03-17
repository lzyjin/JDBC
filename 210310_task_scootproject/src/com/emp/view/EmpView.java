package com.emp.view;

import java.util.Scanner;

import com.emp.controller.EmpController;

public class EmpView {
	
	private EmpController ec = new EmpController();

	public void mainMenu() {
		
		System.out.println("==== 사원 관리 프로그램 ====");
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("1. 전체사원 조회하기");
			System.out.println("2. 이름으로 조회하기");
			System.out.println("3. 사번으로 조회하기");
			System.out.println("4. 사원등록하기");
			System.out.println("5. 사원정보 수정하기");
			System.out.println("6. 사원삭제하기");
			System.out.println("0. 프로그램 종료");
			System.out.print("선택 : ");
			int cho = sc.nextInt();
			
			switch (cho) {
				case 1:
					ec.printAll();
				break;
				case 0:
					System.out.println("프로그램을 종료합니다");
				return;
			}
			
		}
	}
	
}
