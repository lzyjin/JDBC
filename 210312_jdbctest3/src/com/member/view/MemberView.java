package com.member.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.member.controller.MemberController;
import com.member.model.vo.Member;

// 역할 : 애플리케이션을 사용하는 사용자에게 알맞은 화면을 출력해준다 
// 화면별 출력하는 메소드를 가진다 

public class MemberView {
	
	private MemberController mc = new MemberController();
	
	

	// 매개변수
	// 메소드를 실행하는데 꼭 필요한 값을 메소드를 호출한 곳으로부터 전달받기 위해 선언한다. 
	
	// 메소드의 기능에 따라 외부에서 받아올 값(매개변수)의 유무가 결정된다 
	
	public void mainMenu() {		
		
		// 메뉴를 출력하고 메뉴를 선택하면 서비스가 구현될 수 있도록 하는 기능
		
		while(true) {
			
			// 콘솔창에 출력하는 메소드 
			System.out.println("==== 회원관리 프로그램 v.2 ====");
			
			System.out.println("1. 전체회원조회"); // select * from member
			System.out.println("2. 이름으로 조회"); // select * from member where member_name like %값%
			System.out.println("3. 아이디로 조회"); // select * from member where member_id = 값 
			System.out.println("4. 회원 등록"); // insert into member values(값, 값, ....)
			System.out.println("5. 회원 수정"); // update member set 컬럼 = 값
			System.out.println("6. 회원 삭제");  // delete from member where 컬럼 = 값 
			System.out.println("7. 원하는 조건으로 검색하기"); // select * from member where 컬럼 = 값 또는 컬럼 like %값% 
			System.out.println("0. 프로그램 종료"); 
			
			
			Scanner sc = new Scanner(System.in);
			
			System.out.print("메뉴 번호 입력 : ");
				int menu = sc.nextInt();
				
			// 클라이언트가 메뉴를 선택하면 
			// 그 요청을 controller가 처리한다 
			// 그말은 즉슨 controller의 메소드로 연결된다는 의미 
			// -> controller 객체를 필드로 선언할 필요가 있다 
			
				
			switch (menu) {
			
				case 1:
					mc.selectAll();
				break;
				case 2:
					mc.searchName();
				break;
				case 3:
					
				break;
				case 4:
					
				break;
				case 5:
					
				break;
				case 6:
					mc.deleteMember();
				break;
				case 7:
					mc.choiceSearch();
				break;
				case 0:
					System.out.println("프로그램 종료 ");
				return;
				
				default:
				break;
			}
		}
		
		
	}



	public void printAll(List<Member> list) {
		
		System.out.println("---- 전체 회원 조회 ----"); 
		
		for(Member m : list) {
			
			System.out.println(m);
		}
		
	}



	public String searchName() {
		
		System.out.println("---- 이름으로 조회 ----"); // 메뉴이름을 매개변수로 받아 유동적으로 사용할 수 있게 끔 controller에서 분기처리 하자 
													// 210311_jdbctest2의 MemberView 클래스 참고 
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("조회할 이름 : ");
			String name = sc.next();
			
		return name;
		
	}



	public void printData(List<Member> list) {
		
		for(Member m : list) {
			
			System.out.println(m);
		}
	}

	public Map<String, String> colvalInput() {
		
		// 원하는 컬럼과 값을 입력받아 검색
		System.out.println("---- 원하는 조건으로 검색하기 ----");
		
		Scanner sc = new Scanner(System.in);
		
		Map<String, String> param = new HashMap<>();
		
		//sc.nextLine();
		
		System.out.print("컬럼명 : "); // 원래는 조회가 가능한 컬럼명을 출력해서 보여주거나 , 여기서 선택할 수 있게 해야하는데 지금은 간단하게 만듦으로..
			String col = sc.nextLine();
		
		System.out.print("값 : ");
			String val = sc.nextLine();
		
		param.put(col, val);
			
		return param;
	}


}
