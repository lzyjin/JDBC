package com.member.controller;

import com.member.view.View;

public class Controller {
	
	private View v;

	public void mainMenu() {
		
		v = new View();
		
		v.mainMenu(this);
	}

	

}
