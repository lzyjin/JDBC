package com.kh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 	khproject 프로젝트를 만들어서
 * 	오라클의 KH계정의 DEPARTMENT테이블에 있는 data(자료)를 가져와 출력하기
 */

public class Department {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try {
			
			// DataBase에 연결하기위해 
			// 1. Class.forName()메소드를 이용해서 활용할 jdbc드라이버를 등록
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("드라이버 등록 성공 ");
			
			
			
			// 2. DataBase에 연결하기 위해 Connection객체 생성
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "KH", "KH");
			
			System.out.println("DB 접속 성공 ");
			
			
			
			// 3. SQL문을 실행할 Statement객체 생성
			
			stmt = conn.createStatement();
			
			
			
			// 4. 실행할 SQL문을 문자열로 작성
			
			String sql = "SELECT * FROM DEPARTMENT";
			
			
			
			// 5. 작성된 SQL구문을 실행
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("SQL문 실행");
			
			
			// 6. 결과 확인
			while(rs.next()) {
				
				System.out.println(rs.getString("dept_id") + " " + rs.getString("dept_title") + " " + rs.getString("location_id"));
			}
			
			
			
			
			
		} catch(ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch(SQLException e) {
			
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
		
	}

}
