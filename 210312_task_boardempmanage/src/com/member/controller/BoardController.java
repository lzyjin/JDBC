package com.member.controller;

import java.util.List;

import com.member.model.service.BoardService;
import com.member.model.vo.Board;
import com.member.view.BoardView;

public class BoardController {
	
	private BoardView view = new BoardView();
	
	private BoardService service = new BoardService();
	
	

	public void mainMenu() {
		
		view.mainMenu();
		
	}

	public void searchAll() {
		
		List<Board> list = service.searchAll();
	}

}
