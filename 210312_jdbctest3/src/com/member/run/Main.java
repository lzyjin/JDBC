package com.member.run;

import com.member.controller.MemberController;

// PreparedStatement 와 Properties 적용한 버전 ( 수업시간에 선생님이랑 같이 만듦 )

public class Main {

	public static void main(String[] args) {
		
		new MemberController().mainMenu();
		
		// view 객체에서 바로 mainMenu()를 호출하면 되잖아요??!!
		// 웹의 구조상 browser에서 url창에 주소를 적으면 -> request (요청) -> 요청을 받는 곳이 controller
		// 이 흐름에 적응하려고 controller를 먼저 호출하는것 
		
		
		
	}

}
