package com.emp.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.emp.model.vo.Emp;

public class EmpDAO {
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	List<Emp> result = new ArrayList<>();

	public List<Emp>  printAll() {
				
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SCOOT", "TIGER");

			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM EMP";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Emp e = new Emp(rs.getInt("empno"), 
								rs.getString("ename"), 
								rs.getString("job"), 
								rs.getInt("mgr"), 
								rs.getDate("hiredate"), 
								rs.getInt("sal"), 
								rs.getInt("comm"), 
								rs.getInt("deptno"));
				
				result.add(e);
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
		return result;
		
		
	}

	
}
