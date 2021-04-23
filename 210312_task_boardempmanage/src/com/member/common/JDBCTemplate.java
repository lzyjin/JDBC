package com.member.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {

	static Connection conn = null;
	static Properties prop = null;
	
	public static Connection getConnection() {
		
		prop = new Properties();
		
		try {
			
			prop.load(new FileReader("/Users/yejin/jdbcgit/JDBC/210312_task_boardempmanage/src/com/member/common/driver.properties"));
		
			Class.forName(prop.getProperty("driver"));
			
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pw"));
			
			conn.setAutoCommit(false);
			
			
		} catch (IOException | ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return conn;
		
	}
	
	public static void close(Connection conn) {
		
		try {
			
			if(conn != null && !conn.isClosed()) {
				
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement pstmt) {
		
		try {
			
			if(pstmt != null && !pstmt.isClosed()) {
				
				pstmt.close();
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
