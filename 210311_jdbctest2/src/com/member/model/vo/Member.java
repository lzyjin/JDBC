package com.member.model.vo;

import java.sql.Date;

// DB에서 가져온 데이터를 보관하는 클래스 ( Java 형식으로 바꾸기 위해 필요 ) 
// 일종의 데이터를 받는 바구니라고 생각하자 

// 클래스명은 테이블명과 비슷하게 작성한다 
// 멤버변수는 테이블의 컬럼과 개수와 타입을 동일하게, 
// 대신 멤버변수(필드)이름은 해당 테이블의 컬럼과 비슷하게 선언 
public class Member {

	private String memberId;
	private String memberPwd;
	private String memberName;
	private String gender;
	private int age;
	private String email;
	private String phone;
	private String address;
	private String hobby;
	private Date enrollDate;
	
	public Member() {}

	public Member(String memberId, String memberPwd, String memberName, String gender, int age, String email,
			String phone, String address, String hobby, Date enrollDate) {
		
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberName = memberName;
		this.gender = gender;
		this.age = age;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.hobby = hobby;
		this.enrollDate = enrollDate;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Date getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	@Override
	public String toString() {
		return memberId + " " + memberPwd + " " + memberName + " "
				+ gender + " " + age + " " + email + " " + phone + " " + address
				+ " " + hobby + " " + enrollDate;
	}

//	@Override
//	public int hashCode() {
//		
//	}

//	@Override
//	public boolean equals(Object obj) {
//		
//	}
	
	// serializable 
	
	
}