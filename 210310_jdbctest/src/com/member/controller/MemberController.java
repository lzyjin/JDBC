package com.member.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.View;

import com.member.model.dao.MemberDAO;
import com.member.model.vo.Member;
import com.member.view.MemberView;

public class MemberController {
	
	private MemberView mv;
	
	private MemberDAO dao = new MemberDAO();
	
	
	// 메인 메뉴를 호출하는 기능
	public void mainMenu() {
		
		mv = new MemberView();
		mv.mainMenu(this);
	}

	
	
	// DB의 STUDENT계정의 MEMBER테이블에 있는 전체 데이터를 가져오는(조회하는) 서비스
	public void selectAll() {
		
		// DAO 호출해야한다 
		// dao.selectAll();
		// 받은 데이터 view로 출력하기 
		List<Member> list = dao.selectAll();
		
//		for(Member m : list) {
//			
//			System.out.println(m);
//		}
		
		mv.printData(list);
	}



	public void searchName(String name) {
		
		
		List<Member> list = dao.searchName(name);
		
		mv.printData(list);
		
		// or
		
		// 통합한 조회하기 메뉴 사용 
		String name2 = mv.inputData("이름");
		
		List<Member> result = dao.searchName(name2);
		
		mv.printData(result);
		
	}



	public void searchId() {
		
		// 1. 검색할 아이디를 입력받기(정상적으로 입력된 값인지 필터링 하는 기능 추가할 수 있음) -> view에게 요청
		// 2. 입력받은 값을 받으면 그 값이 db에 있는지 확인한다 : dao의 역할
		// 3. dao가 준 결과를 view에서 출력한다 -> view에게 요청
		
		String memeberId = mv.searchId();
		
		Member m = dao.searchId(memeberId);
		
		// 일반 배열을 List로 변경하는 메소드 
		// mv.printData(Arrays.asList(new Member[] {m}));
		
		mv.printData(m);
		
	}
	
	
	public void insertMember() {
		
		Member m = mv.inputMember();
		
		int result = dao.insertMember(m);
		
		mv.printMsg(result > 0 ? "회원을 정상적으로 등록했습니다" : "회원등록을 실패했습니다");
		
	}



	public void updateMember() {
		
		String[] str = mv.updateMember();
		
		dao.updateMember(str);
		
		
	}

}
