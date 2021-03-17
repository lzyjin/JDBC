package com.member.common;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class JDBCTemplate {

	static Connection conn = null;
	static Properties prop = null;
	
	public static Connection getConnection() {
		
		prop = new Properties();
		
		try {
			prop.load(new FileReader("/Users/yejin/jdbcgit/JDBC/210312_task_boardempmanage/src/com/member/common/driver.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Class.forName(prop.getProperty("driver"));
		
		return conn;
		
	}
}
