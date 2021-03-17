package com.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.member.model.vo.Member;

// DB접속정보를 가지고 DB에 접속해서 SQL구문을 실행하는 기능을 하는 클래스 
// SQL실행결과를 호출한 쪽으로 반환해준다 
public class MemberDAO {

	// db에서 한개의 row는 자바에서의 클래스 
	// 여러개의 클래스를 받아올 수 있는 리턴타입 필요 
	// 각 행은 순서가 있으니까 List타입이 적합하겠다 
	
	public List<Member> selectAll() {
		
		
		// DB에 연결해서 ResultSet으로 데이터를 받아오기
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null; // select문을 실행시킬것이 아니라면 resultset객체 필요 없다 
	
		// 여기에 반환형에 대한 선언 작성
		List<Member> result = new ArrayList<>();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ClassNotFoundException

			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT"); // SQLException
			
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM MEMBER";
			
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()) {
				/*
				System.out.println(rs.getString("member_id") + " " +
									// rs.getString("member_pwd")+
									rs.getString("member_name")+ " " +
									rs.getString("gender")+ " " +
									rs.getInt("age")+ " " +
									rs.getString("email")+ " " +
									rs.getString("phone")+ " " +
									rs.getString("address")+ " " +
									rs.getString("hobby")+ " " +
									rs.getDate("enroll_date"));
				*/
				
				// 가져온 데이터를 result 객체에 넣기 
				// result는 Member타입만 저장할 수 있는 List
				
				
				// Member타입 객체 m은 꼭 while문 안에서 생성해야한다 
				// 그렇지 않으면 힙영역에 생성된 객체 m의 주소가 다 동일해져버려서 
				// 데이터의 마지막 ROW의 값만 여러개 나오게 된다 
				
				Member m = new Member(rs.getString("member_id"),
										rs.getString("member_pwd"), 
										rs.getString("member_name"), 
										rs.getString("gender"), 
										rs.getInt("age"), 
										rs.getString("email"), 
										rs.getString("phone"), 
										rs.getString("address"), 
										rs.getString("hobby"), 
										rs.getDate("enroll_date"));
				result.add(m);
			}
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(rs != null && !rs.isClosed()) rs.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(conn != null && !conn.isClosed()) conn.close();
				
			} catch(SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return result;
	}

	// 이름으로 검색 
	public List<Member> searchName(String name) {
				
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<Member> list = new ArrayList<>();
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			stmt = conn.createStatement();
			
			// oracle에서 썼던 방식과 동일하게 문자열은 ''안에 표시 
			// String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME = 'name'"; // 이렇게 작성하면 안된다 
			// String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME = '" + name + "'";
			String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%" + name + "%'";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				// 이렇게 하면 순서가 헷갈릴수 있고, 어디가 잘못되었는지 찾기 힘드니까
				// setter로 값을 대입해주는게 좋다 
//				Member m = new Member(rs.getString("member_id"),
//						rs.getString("member_pwd"), 
//						rs.getString("member_name"), 
//						rs.getString("gender"), 
//						rs.getInt("age"), 
//						rs.getString("email"), 
//						rs.getString("phone"), 
//						rs.getString("address"), 
//						rs.getString("hobby"), 
//						rs.getDate("enroll_date"));
					
				Member m = new Member();
				
				m.setMemberId(rs.getString(1)); // 헷갈릴 위험성 있기 때문에 컬럼명을 직접 작성해주자 
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
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(rs != null && !rs.isClosed()) rs.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(conn != null && !conn.isClosed()) conn.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
	// 아이디로 조회 
	public Member searchId(String memeberId) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Member m = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = '" + memeberId + "'";
			
			rs = stmt.executeQuery(sql);

			
			// member_id 가 pk라서 unique를 만족하므로 결과값은 하나 
			// -> 반복문 쓸 필요 없음 
			if(rs.next()) {
				
				// 이렇게 하면 순서가 헷갈릴수 있고, 어디가 잘못되었는지 찾기 힘드니까
				// setter로 값을 대입해주는게 좋다 
				m = new Member(rs.getString("member_id"),
						rs.getString("member_pwd"), 
						rs.getString("member_name"), 
						rs.getString("gender"), 
						rs.getInt("age"), 
						rs.getString("email"), 
						rs.getString("phone"), 
						rs.getString("address"), 
						rs.getString("hobby"), 
						rs.getDate("enroll_date"));
						
			}
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(rs != null && !rs.isClosed()) rs.close();
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(conn != null && !conn.isClosed()) conn.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		return m;
		
	}

	
	
	// insert문을 실행하면 삽입된 행의 수(정수)가 출력되므로 
	// 리턴타입을 int로 맞춰줬다
	
	// 회원 등록 
	public int insertMember(Member m) {
		
		Connection conn = null;
		Statement stmt = null;
		
		int result = 0;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			
			// 트랜잭션 처리(commit, rollback)을 개발자가 설정할 수 있게 한다 
			conn.setAutoCommit(false);
			
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO MEMBER VALUES('" + m.getMemberId() + "','"
													   + m.getMemberPwd() + "', '"
													   + m.getMemberName() + "', '"
													   + m.getGender() + "'," 
													   + m.getAge() + ", '" 
													   + m.getEmail() + "', '"
													   + m.getPhone() + "', '" 
													   + m.getAddress() + "', '"
													   + m.getHobby() + "', "
													   + "SYSDATE)";
			
			result = stmt.executeUpdate(sql);
			
			// insert문은 트랜잭션처리해야한다 
			// Connection 객체에 트랜잭션을 처리해주는 메소드가 있다
			// commit(), rollback() 
			
			if(result > 0) {
				
				conn.commit();
			} else {
				
				conn.rollback();
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			//
		} finally {
			
			try {
				
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(conn != null && !conn.isClosed()) conn.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		return result;
		
	}

	public void updateMember(String[] str) {
		
		Connection conn = null;
		Statement stmt = null;
		
		String id = str[0];
		String newData = str[1];
		String column = str[2];
		
		 
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			conn.setAutoCommit(true);
			
			stmt = conn.createStatement();
			
			
			String sql = "UPDATE MEMBER SET " + column + " = '" + newData + "' WHERE MEMBER_ID = '" + id + "'";
			
			int result = stmt.executeUpdate(sql);
			
			System.out.println(result);
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			try {
				
				if(stmt != null && !stmt.isClosed()) stmt.close();
				if(conn != null && !conn.isClosed()) conn.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	}
	
}
