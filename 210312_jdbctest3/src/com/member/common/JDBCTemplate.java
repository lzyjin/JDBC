package com.member.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

// 역할 : Connection 객체를 생성하는 공용 메소드,
// 		 DB 실행에 필요한 객체를 반환하는 공용 메소드,
// 		 commit, rollback을 처리하는 공용 메소드를 선언한다 

// properties클래스를 이용해서 db연결에 필요한 데이터를 가져와 처리하기 


public class JDBCTemplate {

	
	
	public static Connection getConnection() {
		
		Connection conn = null;
		
		// Properties 객체 생성 
		Properties prop = new Properties();
		
		try {
			
			// Class.forName("oracle.jdbc.driver.OracleDriver");
			// conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			String path = JDBCTemplate.class.getResource("./").getPath();
			
			prop.load(new FileReader(path + "/driver.properties"));
			
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pw"));
			
			
			// 트랜잭션을 개발자가 관리하기 위한 설정 
			conn.setAutoCommit(false);
			
			
			
		} catch ( ClassNotFoundException | SQLException | IOException e) {
			
			e.printStackTrace();
		}
		
		return conn;
	
	}
	
	// 객체를 반환하는 공용 메소드
	public static void close(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) conn.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
			
			try {
				
				if(stmt != null && !stmt.isClosed()) stmt.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
	public static void close(ResultSet rs) {
		
		try {
			
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	// 트랜잭션 처리하는 공용 메소드 
	public static void commit(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) conn.commit();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
			
			try {
				
				if(conn != null && !conn.isClosed()) conn.rollback();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
	
	
	}
