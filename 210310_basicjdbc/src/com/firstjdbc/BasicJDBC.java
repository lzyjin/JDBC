package com.firstjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BasicJDBC {

	public static void main(String[] args) {
		
		
		// JDBC활용 객체는 반드시 반환해야한다
		// conn, stmt, rs 는 다 Exception처리 해야한다 -> try문 안에 선언된다 -> 정상적으로 반환될 수 없다 
		// try문 밖에서 지역변수로 선언해야한다
		// 지역변수는 반드시 초기화해야하므로 null로 초기화 
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		
		
		// DataBase에 연결하기
		// 1. Class.forName()메소드를 이용해서 활용할 jdbc드라이버를 등록한다 (OracleDriver 클래스 이용) 
		// driver이름 : oracle.jdbc.driver.OracleDriver (암기)
		// 반드시 예외처리 필요 
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver"); // ojdbc.jar 파일을 넣지 않으면 ClassNotFoundException 발생 -> 예외처리 필요 
			System.out.println("드라이버 등록 성공");
			
			
			// 2. DataBase에 연결하기 위해 Connection객체 생성 
			// DriverManager객체의 getConnection()메소드를 이용해서 Connection객체 생성
			
			// getConnection()메소드의 매개변수 -> 3개의 문자열 매개변수
			// DB접속 주소, 사용자 계정, 사용자 비밀번호
			
			// DB접속 주소 : jdbc:oracle:thin:@db서버의 ip주소:db포트번호:db의 sid
			// 				예)jdbc.oracle:thin:@localhost:1521:xe
			// 사용자 계정 : 문자열로 작성 -> 예) "student"
			// 사용자 비밀번호 : 문자열로 작성 -> 예) "student" (git에 올릴때 정보 털리지 않게 주의해야함)
			
			
			// Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "STUDENT", "STUDENT");
			
			System.out.println("DB 접속 성공");
			
			
			// 3. SQL문을 실행할 객체생성 (Connection객체를 이용해서)
			// Statement객체 생성
			// Connection객체이의 createStatement()메소드를 이용해서 생성
			
			// Statement stmt = conn.createStatement();
			stmt = conn.createStatement();
			
			
			// 4. 실행할 SQL문을 문자열로 작성
			// SQL DEVELOPER에서 사용한 구문을 사용하면 된다. (DML - SELECT, INSERT, UPDATE, DELETE)
			// ""안에 SQL구문을 작성할때는 반드시 ;를 제외한다 
			
			String sql = "SELECT * FROM MEMBER";
			
			
			
			
			// 5. 작성된 SQL구문을 실행 
			// Statement.executeQuery() 또는 Statement.excuteUpdate() 메소드를 이용
			
			// Statement.executeQuery(실행할 SQL구문) : SELECT문을 실행할 때 사용 -> return type : ResultSet
			// Statement.excuteUpdate(실행할 SQL구문) : INSERT, UPDATE, DELETE문을 실행할 때 사용 -> return type : int
			
			// ResultSet rs = stmt.executeQuery(sql); // SQL구문을 실행 메소드 
			rs = stmt.executeQuery(sql);
			
			System.out.println("SQL문 실행");
			
			
			
			
			// 6. 결과 확인
			// SELECT문의 실행결과는 ResultSet객체에 테이블 형식의 데이터가 저장되어있다
			// ResultSet객체의 데이터를 한 행씩 가져와야한다
			
			// ResultSet.next() : 결과로 가져온 테이블의 ROW를 한 개씩 지칭한다 ( 각 한 개의 ROW를 지정 )
			// 한번 읽으면 첫번째 행 -> next()로 한번 더 읽으면 두번째 행 -> ...
			
			// 컬럼에 있는 데이터를 가져오려면 
			// getString("컬럼명"), getInt("컬럼명"), getDate("컬럼명"), getDouble("컬럼명") 메소드 이용 
			
			// 반복문을 이용한다
			
			// 자동으로 VARCHAR2, CHAR -> String 타입으로 
			// NUMBER -> int
			// DATE -> java.util.date? java.sql.date ? 변환해준다 
			
		/*
			rs.next(); // 첫번째 ROW 지정 
			String memberId = rs.getString("member_id"); // 매개변수 컬럼명은 대소문자를 구분하지 않는다
			String memberPwd = rs.getString("member_pwd");
			String memberName = rs.getString("member_name");
			int age = rs.getInt("age");
			
			System.out.println(memberId+ " " + memberPwd + " " + memberName + " " + age); // admin admin 관리자 30
			
			
			// 두번째 ROW를 지정하고싶으면 
			rs.next(); 
			memberId = rs.getString("member_id"); 
			memberPwd = rs.getString("member_pwd");
			memberName = rs.getString("member_name");
			age = rs.getInt("age");
			
			System.out.println(memberId+ " " + memberPwd + " " + memberName + " " + age); // user11 pass11 홍길동 23
		*/
			
			
			// 똑같은 코드가 중복되니까 반복문으로 중복을 줄여주자
			
			// rs.next()의 리턴값은 다음 ROW가 있으면 true,없으면 false이므로 
			// while문의 조건식으로 사용할 수 있다
			while(rs.next()) {
				
			/*
				String memberId = rs.getString("member_id");
				String memberPwd = rs.getString("member_pwd");
				String memberName = rs.getString("member_name");
				int age = rs.getInt("age");
				
				System.out.println(memberId+ " " + memberPwd + " " + memberName + " " + age); 
			*/
				System.out.println(rs.getString("member_id") + " " + rs.getString("member_pwd") + " " + 
									rs.getString("member_name") + " " + rs.getInt("age"));	
			}
			
			
			
			// INSERT문 실행하기
			// 컬럼에 들어갈 값을 작성할 때 리터럴 표시를 정확하게 해줘야한다 
			// 문자 : ''
			// 숫자 : 1234
			// 날짜 : SYSDATE 또는 '93/07/10'
			
			sql = "INSERT INTO MEMBER VALUES('lize1234', 'lize1234', '김예진', 'F', 20, 'lize@naver.com', '01012345678', '서울시 강남구', '독서,운동', SYSDATE)";
			
			int result = stmt.executeUpdate(sql);
			// System.out.println(result); // result는 실행한 결과의 row 수 
			System.out.println(result > 0 ? "입력 성공" : "입력 실패");
			
			
			// insert, update, delete 트랜잭션 처리 해야한다. 
			
			// 7. 생성된 객체 닫기
			// connection, statement, resultset 객체는 입출력하는것이므로 반드시 반환해줘야한다 
			// close()메소드로 
			// 반환하는 순서는 생성의 역순으로
			// resultset.close() -> statement.close() -> connection.close()
			
			// 여기서 닫으면 안된다
			// 예외발생하면 이 코드는 실행이 안되기 때문 
		/*
			rs.close();
			stmt.close();
			conn.close();
		*/
			
			
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			
			// rs, stmt, conn을 try문 바깥에서 사용하기위해
			// try문 바깥에서 변수를 선언해준다 
			// 지역변수로 
			
			// 객체 반환 
			
			try {
				
				//  rs, stmt, conn가 null이 아니고, 닫혀있지 않으면 닫아라 
				
				if( rs != null && !rs.isClosed() ) rs.close();
				if( stmt != null && !stmt.isClosed() ) stmt.close();
				if( conn != null && !conn.isClosed() ) conn.close();
				
			} catch(SQLException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		
	}

}
