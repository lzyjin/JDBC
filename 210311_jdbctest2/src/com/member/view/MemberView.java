package com.member.view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.member.controller.MemberController;
import com.member.model.vo.Member;

//view클래스는 기능을 수행할 때 controller를 호출

public class MemberView {

	// 사용자에게 보여질 화면을 구성하는 클래스
	// 화면(콘솔)에 출력되는 메소드(기능)을 가지고 있음 
	// 나중에 화면은 html, css, js, jq ... 
	
	private MemberController mc;
	
	public void mainMenu(MemberController mc) {
		
		this.mc = mc;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("==== 회원 관리 프로그램 v.2 ====");
		
		while(true) {
			
			System.out.println("1. 전체회원 조회하기"); // 숙제 : 프로젝트 새로 만들어서 이 기능 다 만들기 
			System.out.println("2. 이름으로 조회하기"); // PreparedStatement 사용해서 만들기 
			System.out.println("3. 아이디로 조회하기");
			System.out.println("4. 회원등록하기");
			System.out.println("5. 회원정보 수정하기");
			System.out.println("6. 회원삭제하기");
			System.out.println("0. 프로그램 종료");
			
			System.out.print("메뉴 번호 선택 : ");
				int cho = sc.nextInt();
			
			switch(cho) {
			
				case 1:
					mc.selectAll();
				break;
				case 2:
					
				break;
				case 3:
					
				break;
				case 4:
					mc.insertMember();
				break;
				case 5:
					
				break;
				case 6:
					
				break;
				case 0:
					System.out.println("프로그램을 종료합니다");
				return;
					
			}
		}
		
	}

	
	
	public void printData(List<Member> data) {
		
		System.out.println("==== 조회한 회원 정보 ===");
		
		for(Member m : data) {
			
			System.out.println(m);
		}
		
	}
	
	
	
	public String inputData(String title) {
			
			Scanner sc=new Scanner(System.in);
			
			System.out.println("==== 검색할 "+title+" 입력 ====");
			
			return sc.nextLine();
		}
	
	
	
	public void printData(Member m) {
		
		System.out.println("===== 검색결과 =====");
		
		System.out.println(m);
	}
	
	
	public Member inputMember() {
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("===== 회원등록 =====");
		
		System.out.print("아이디 : ");
			String memberId=sc.nextLine();
		
		System.out.print("비밀번호 : ");
			String memberPwd=sc.nextLine();
		
		System.out.print("이름 : ");
			String memberName=sc.nextLine();
		
		System.out.print("성별(M/F) : ");
			String gender=sc.nextLine();
		
		System.out.print("나이 : ");
			int age=sc.nextInt();
		
		System.out.print("이메일 : ");
			sc.nextLine();
			String email=sc.nextLine();
		
		System.out.print("전화번호 : ");
			String phone=sc.nextLine();
		
		System.out.print("주소 : ");
			String address=sc.nextLine();
		
		System.out.print("취미(,) : ");
			String hobby=sc.nextLine();
		
		return new Member(memberId, memberPwd,memberName,gender,age,email,phone,address,hobby,null);
	}
	
	
	public void printMsg(String msg) {
		
		System.out.println("======== 시스템 알림 =========");
		
		System.out.println(msg);
		
		System.out.println("============================");
	}
	
	
}
