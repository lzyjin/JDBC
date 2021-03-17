package com.emp.controller;

import java.util.List;

import com.emp.model.dao.EmpDAO;
import com.emp.model.vo.Emp;
import com.emp.view.EmpView;

public class EmpController {
	
	
	private EmpDAO dao = new EmpDAO();

	
	
	public void mainMenu() {
		
		EmpView view = new EmpView();
		
		view.mainMenu();
	}

	
	
	public void printAll() {
		
		List<Emp> list = dao.printAll();
		
		for(Emp e : list) {
			
			System.out.println(e);
		}
		
	}

}
