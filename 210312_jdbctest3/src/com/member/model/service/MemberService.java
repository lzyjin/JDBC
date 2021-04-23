package com.member.model.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.member.common.JDBCTemplate;
import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

import static com.member.common.JDBCTemplate.getConnection;
import static com.member.common.JDBCTemplate.close;


// 역할 : DB 연결을 위해 필요한 Connection객체를 생성하고, Connection객체를 관리하고, 트랜잭션처리(commit, rollback)을 한다 

public class MemberService {
	
	
	// 필드 
	private MemberDao dao = new MemberDao();

	
	public List<Member> selectAll() {
		
		// 공통적으로 필요한 부분을 
		// common 패키지에
		// JDBCTemplate 클래스로 만들자 
		
		// JDBCTemplate의 메소드를 통해 Connection 객체 생성 
		
		Connection conn = getConnection();
		
		List<Member> list = dao.selectAll(conn);
		
		
		// Connection객체를 관리
		
		close(conn);
		
		
		
		return list;
	}

	
	
	
	
	public List<Member> searchName(String name) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Member> list = dao.searchName(conn, name);
		
		return list;
	}


	

	public List<Member> choiceSearch(Map<String, String> param) {
		
		// 이부분 기억하기 
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Member> list = dao.choiceSearch(conn, param);
		
		close(conn);
		
		return list;
	}
	
	

}
