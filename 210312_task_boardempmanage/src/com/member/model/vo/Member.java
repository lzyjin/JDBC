package com.member.model.vo;

public class Member {
	
	/*
	 *  IDX NUMBER PRIMARY KEY,
    	MEMBER_ID VARCHAR2(10) NOT NULL,
    	MEMBER_PWD VARCHAR2(10) NOT NULL,
    	MEMBER_NAME VARCHAR2(20) NOT NULL,
    	EMAIL VARCHAR2(30),
    	ADDRESS VARCHAR2(100),
    	PHONE VARCHAR2(11),
    	ENROLL_DATE DATE
	 */
	
	private int idx;
	private String memberId;
	private String memberPwd;
	private String memberName;
	private String email;
	private String address;
	private String phone;
	private String enrollDate;
	
	
	public Member() {}

	
	public Member(int idx, String memberId, String memberPwd, String memberName, String email, String address,
			String phone, String enrollDate) {
		this.idx = idx;
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.enrollDate = enrollDate;
	}


	public int getIdx() {
		return idx;
	}


	public void setIdx(int idx) {
		this.idx = idx;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getMemberPwd() {
		return memberPwd;
	}


	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEnrollDate() {
		return enrollDate;
	}


	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}


	@Override
	public String toString() {
		return idx + ", " + memberId + ", " + memberPwd + ", "
				+ memberName + ", " + email + ", " + address + ", " + phone + ", "
				+ enrollDate;
	}
	
	
	

}
