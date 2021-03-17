package com.member.model.service;

import java.sql.Connection;
import java.util.List;

import com.common.JDBCTemplate;
import com.member.model.dao.MemberDao;
import com.member.model.vo.Member;

public class MemberService {

	// Connection 객체를 관리하는 기능, 	connection 객체 반환, 트랜잭션 처리 한다 
	private MemberDao dao = new MemberDao();
	

	public List<Member> selectAll() {
		
		// 기능 1
		// Connection 객체 가져오기 
		Connection conn = JDBCTemplate.getConnection();
		
		// 가져온 connection를 dao 에 전달
		List<Member> list = dao.selectAll(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}


	public int inertMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = dao.insertMember(conn, m);
		
		
		// 트랜잭션 처리 
		// commit(), rollback()
		if(result >0) JDBCTemplate.commit(conn);
		else JDBCTemplate.rollback(conn);
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	
	
}
