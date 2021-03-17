package com.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.member.model.vo.Member;

import static com.member.common.JDBCTemplate.close;


// 전달받은 db정보를 활용해서 db에 접속한 후 해당하는 서비스의 데이터를 조회, 조작하는 것 

public class MemberDao {
	
	// properties 객체 생성 -> 각 메소드에서 이 객체가 다 필요하므로 필드로 선언 
	Properties prop = new Properties();
	
	// 기본생성자
	public MemberDao() {
		
		try {
			
			prop.load(new FileReader("resources/sql/member_sql.properties")); // 암기 
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	// -> sql구문 필요없어짐 
	

	public List<Member> selectAll(Connection conn) {
		
		// sql객체를 실행하는 객체 : Statement, PreparedStatement
		
		PreparedStatement pstmt = null;
		
		// db에서 가져온 데이터를 보관할 객체가 필요하다 : ResultSet
		
		ResultSet rs = null;
		
		List<Member> list = new ArrayList<>();
		
		// 위 3개의 객체는 리턴하기 위해 trycatch문 밖에 선언 
		
		
		
		
		//PreparedStatement객체는 sql과 같이 생성해서 sql문을 메소드로 완성 후 실행하는 객체 
		
		// String sql = "SELECT * FROM MEMBER";
		
		try {
			
			// pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(prop.getProperty("selectAll"));
			
			// 위치홀더 (?표시) 가 매개변수로 들어온 쿼리문에 있으면  set***으로 값을 설정하고
			// 위치홀더가 없을 경우 값 설정 없이 그냥 사용 
			
			rs = pstmt.executeQuery();
			
			// db에서 가져오는 row가 여러개이면 while, 
			// 					 1개이하이면 if 로 처리 
			
			
			while (rs.next()) {
				
				Member m = new Member();
				
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
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		
		return list;
	}

	public List<Member> searchName(Connection conn, String name) {
		
		PreparedStatement pstmt = null;
		
		ResultSet rs = null;
		
		List<Member> list = new ArrayList<>();
		
		// 쿼리문에 LIKE 사용시 방법 2가지
		   String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE ?";
		// or
		// String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%' || ? || '%'";
		
		// 원래 구문 : SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%김%' 
		
		// 자주 하는 실수 주의
		// String sql = "SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%?%'";
		// 이렇게 하는건 SELECT * FROM MEMBER WHERE MEMBER_NAME LIKE '%'김'%' 이렇게 쓰는것과 같다 ( 에러남)
		
		
		try {
			
			// 순서 암기하기 
			
			// pstmt = conn.prepareStatement(sql);
			pstmt = conn.prepareStatement(prop.getProperty("selectName"));
			
			// 방법 2가지 
			pstmt.setString(1, "%" + name + "%"); // 매개변수 m.getMemberId()는 자동으로 ''안에 들어있다 -> sql구문작성시 ''안쓰고 바로 ? 작성
			// or
			// pstmt.setString(1, name); 
			
			
			rs = pstmt.executeQuery(); // PreparedStatement는 쿼리문을 가지고 있어서 매개변수로 안넣어도 된다 
			
			while(rs.next()) {
				
				Member m = new Member();
				
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
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	public List<Member> choiceSearch(Connection conn, Map<String, String> param) {
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> list = new ArrayList<>();
		
		String col = "";
		String val = "";
		
		
		// 파라미터를 받아오려면 
		// Iterator 
		Set<Map.Entry<String, String>> entry = param.entrySet();
		
		for(Map.Entry<String, String> e : entry) {
			
			col = e.getKey();
			val = e.getValue();
		}
		
		// String sql = "SELECT * FROM MEMBER WHERE ? LIKE ?";
		// 컬럼명은 위치홀더 사용하지 말기 
		// String sql = "SELECT * FROM MEMBER WHERE " + col + " LIKE ?";
		// or
		// String sql = "SELECT * FROM MEMBER WHERE # LIKE ?";
		// sql = sql.replace("#", col);
		
		
		
		String sql = prop.getProperty("choiceSelect");
		sql = sql.replace("#", col); // 어떻게 해야 서울시민만 나오게 할 수 있지 ???????? -> %값% 이렇게 작성해야함 
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			// pstmt.setString(1, col); // 쿼리문에 '컬럼명' 이렇게 들어감 -> 컬럼명은 ''없이 작성해야하니까 -> 에러 
			pstmt.setString(1, "%" + val + "%");
			
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				
				Member m = new Member();
				
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
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			close(rs);
			close(pstmt);
		}
		
		return list;
	}

	
}
