package com.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.common.JDBCTemplate;
import com.member.model.vo.Member;

public class MemberDao {

	
	public List<Member> selectAll(Connection conn) {
		
		Statement stmt = null;
		ResultSet rs  = null;
		
		List<Member> list = new ArrayList();
		
		try {
			
			String sql = "SELECT * FROM MEMBER";
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Member m=new Member();
				
				m.setMemberId(rs.getString("member_id"));
				m.setMemberPwd(rs.getString("member_pwd"));
				m.setMemberName(rs.getString("member_name"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setEmail(rs.getString("email"));
				m.setPhone(rs.getString("phone"));
				m.setAddress(rs.getString("address"));
				m.setHobby(rs.getString("hobby"));
				m.setEnrollDate(rs.getDate("enroll_date"));
				
				list.add(m);
			}
			
		} catch(SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}

	
	
	
	public int insertMember(Connection conn, Member m) {
		
		// Statement stmt = null;
		PreparedStatement pstmt = null;
		// 위치홀더(?표시)를 이용해서 데이터의 자료형 표시를 대신 해준다
		// 특정 메소드(setString, setInt,,,)로 해당 위치 홀더에 값만 대입하면 된다 
		// 인덱스로 위치를 셀 수 있음 
		// 인덱스는 1부터 시작 
		// ? 만 세기때문에 실제로 값을 넣으면 그부분은 건너뛰고 이어서 숫자를 센다 
		// 예시 : 
		
		
		int result = 0;
		
		try {
			
		/*
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO MEMBER VALUES('" + m.getMemberId() + "', " +
													"'" + m.getMemberPwd() + "', " +
													"'" + m.getMemberName() + "', " + 
													"'" + m.getGender() + "', " +
													 m.getAge() +
													"'" + m.getEmail() + "', " +
													"'" + m.getPhone() + "', " +
													"'" + m.getAddress() + "', " +
													"'" + m.getHobby() + "', " +
													"SYSDATE)";
			
			result = stmt.executeUpdate(sql);
		*/		
		
			
			// PreparedStatement 객체를 생성할때는 connection의 prepareStatement()메소드를 이용한다 
			// 매개변수로 sql구문을 넣어줘야한다 
			
			String sql = "INSERT INTO MEMBER VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
			
			// PreparedStatement에 위치홀더 안써도 된다 
			
			
			// 만약 이름으로 검색 기능을 PreparedStatement로 만든다고 하면
			// "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?"
			
			pstmt = conn.prepareStatement(sql);
			
			// 위치홀더가 선언된 곳에 값을 대입한다
			// ?가 9개이기때문에 9개 다 넣어줘야 에러가 안난다 
			// 순서 잘 지켜서 넣기 !! 
			
			pstmt.setString(1, m.getMemberId()); // 매개변수 m.getMemberId()는 자동으로 ''안에 들어있다 -> sql구문작성시 ''안쓰고 바로 ? 작성
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			// 하나라도 넣지 않으면 인덱스에서 누락된 IN 또는 OUT 매개변수 :: 8  
			// 이런식으로 예외가 발생한다 
			
			
			// pstmt에서 쿼리를 실행할때는 executeQuery(), executeUpdate() 
			// 대신 매개변수가 없다. 
			result = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			// JDBCTemplate.close(stmt);
			
			// PreparedStatement는 Statement를 상속받았기 때문에 Statement타입 매개변수로 들어갈 수 있다 
			JDBCTemplate.close(pstmt);
		}
		
		return 0;
	}
	
	
}
