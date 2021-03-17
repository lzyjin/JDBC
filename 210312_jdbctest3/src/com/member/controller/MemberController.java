package com.member.controller;

import java.util.List;
import java.util.Map;

import com.member.model.service.MemberService;
import com.member.model.vo.Member;
import com.member.view.MemberView;

// member서비스를 관리하는 객체
// 역할 : 클라이언트에게 특정 서비스기능을 요청받아서 처리한다
// 필요한 화면(view)이나 데이터(db)를 가져오는(model)것을 중간에서 조절하는 클래스 

// controller의 메소드는 리턴값이 없다 -> void 

public class MemberController {
	
	private MemberService ms = new MemberService();
	
	

	public void mainMenu() {
	
		// 클라이언트로부터 메인 화면을 보여달라는 요청을 받음 
		
		// this를 쓰는게 어려우면 
		// new로 MemberView 객체를 매번 생성해서 메인메뉴를 호출해도 된다 
		new MemberView().mainMenu();
		
	}

	public void selectAll() {
		
		// db의 student계정의 member테이블 전체 데이터를 가져와 출력하는 서비스 
		
		// db에 접근해야할 때는 Service객체에 요청해야한다 
		
		List<Member> list = ms.selectAll(); // 리턴값이 member테이블 전체 데이터 -> List객체로 받자 
		
		
		// 받은 데이터를 화면에 출력하려고
		new MemberView().printAll(list);
		
		
	}

	
	// 사용자가 검색할 이름을 입력하면 그 값이 db에 있는지 확인하고 그 결과를 출력해주는 서비스 
	public void searchName() {
		
		String name = new MemberView().searchName();
		
		List<Member> list = ms.searchName(name);
		
		new MemberView().printData(list);
	}

	
	// 사용자가 원하는 조건으로 검색하기 서비스 
	public void choiceSearch() {
		
		Map<String, String> param = new MemberView().colvalInput();
		
		List<Member> list = ms.choiceSearch(param); // 데이터가 저장되어있는 최대치(10개)를 받기 위해 List로 
		
		new MemberView().printData(list);
	}

	// 사용자가 아이디를 입력하면 해당하는 회원의 정보를 삭제하는 서비스 
	public void deleteMember() {
				
		// String id = new MemberView().// -> 아이디로 조회하는 메소드를 활용해서 만들수 있게 해보자 
	}

	
}
