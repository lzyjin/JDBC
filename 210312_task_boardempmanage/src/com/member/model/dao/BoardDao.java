package com.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Properties;

import com.member.model.vo.Board;
import com.sun.net.httpserver.Authenticator.Result;

public class BoardDao {
	
	Properties prop = new Properties();
	
	public BoardDao() {
		
		try {
			prop.load(new FileReader("resources/sql.properties"));
			
			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	

	public List<Board> searchAll() {
		
		return null;
	}

	
	
	

}
