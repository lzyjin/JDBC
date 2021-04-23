package com.member.model.vo;

public class Board {
	
	private int idx;
	private String div;
	private String title;
	private String contents;
	private int writerNumber;
	private String writeDate;
	
	public Board() {}

	public Board(int idx, String div, String title, String contents, int writerNumber, String writeDate) {
		this.idx = idx;
		this.div = div;
		this.title = title;
		this.contents = contents;
		this.writerNumber = writerNumber;
		this.writeDate = writeDate;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getDiv() {
		return div;
	}

	public void setDiv(String div) {
		this.div = div;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getWriterNumber() {
		return writerNumber;
	}

	public void setWriterNumber(int writerNumber) {
		this.writerNumber = writerNumber;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	@Override
	public String toString() {
		return idx + ", " + div + ", " + title + ", " + contents + ", "
				+ writerNumber + ", " + writeDate;
	}
	
	
	
	

}
