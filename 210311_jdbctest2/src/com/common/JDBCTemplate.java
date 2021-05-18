package com.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {

	// DAO에서 사용하는 
	// Connection 생성, Statement 반환, ResultSet 반환, Connection 반환, 트랜잭션 처리 메소드를
	// 공통으로 관리하는 클래스 
	
	// 각 메소드는 모두 어플리케이션 안에서 공통으로 사용하기 때문에 
	// static으로 관리한다 
	// -> 메소드는 모두 static으로 선언 
	
	// Connection 생성 메소드 
	public static Connection getConnection() {
		
		Connection conn = null;
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DriverManager.getConnection() 매개변수 : JDBC_url,"아이디","비밀번호"
			// JDBC_URL 구성 = JDBC:oracle:thin:@IP주소:포트:SID
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			// 트랜잭션 오토커밋 방지
			conn.setAutoCommit(false);
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	// 객체 반환 메소드 : Connection, Statement, ResultSet
	public static void close(Connection conn) {
			
		try {
			
			if(conn != null && !conn.isClosed()) {
				
				conn.close();
			}
				
		} catch (SQLException e) {
				
			e.printStackTrace();
		}
		
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt != null && !stmt.isClosed()) {
				
				stmt.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void close(ResultSet rs) {

		try {
			if(rs != null && !rs.isClosed()) {
				
				rs.close();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	// 트랜잭션 처리 : Connection에 대해 commit, rollback
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	public static void rollback(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
}
