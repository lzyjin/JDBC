package com.member.model.service;

import java.sql.Connection;
import java.util.List;

import static com.member.common.JDBCTemplate.getConnection;
import static com.member.common.JDBCTemplate.close;
import com.member.model.dao.BoardDao;
import com.member.model.vo.Board;

//역할 : DB 연결을 위해 필요한 Connection객체를 생성하고, Connection객체를 관리하고, 트랜잭션처리(commit, rollback)을 한다 

public class BoardService {
	
	
	
	private BoardDao dao = new BoardDao();
	
	

	public List<Board> searchAll() {
		
		Connection conn = getConnection();
		
		List<Board> list = dao.searchAll();
		
		close(conn);
		
		return list;
	}

	
}
