package com.jardin.shop11.dto;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class JoinDto {

	public JoinDto(String email1, String email2, String phone1, String phone2, String phone3, String year, String month,
			String day, String address1, String address2) throws ParseException {
		this.email = email1 + "@" + email2;
		this.phone = phone1 + "-" + phone2 + "-" + phone3;

		String birth = year + "/" + month + "/" + day;
		java.util.Date date = new SimpleDateFormat("yyyy/MM/dd").parse(birth);
		Date birthDate = new Date(date.getTime());
		this.birth = birthDate;
		this.address = address1 + " " + address2;
	}

	private int memNo; // 회원 고유 번호
	private String memName; // 회원이름o
	private String memId; // 회원 아이디o
	private String memPw; // 회원 비밀번호o
	private String email1; // 앞부분
	private String email2; // 뒷부분
	private String email; // 이메일o
	private String emailReceive; // 이메일 수신 동의o
	private String address1; // 우편번호
	private String address2; // 도로명주소 및 상세주소
	private String address; // 주소o
	private String phone1; // 010
	private String phone2; // 4147
	private String phone3; // 8458
	private String phone; // 핸드폰번호o
	private String smsReceive; // sms수신 동의o
	private Date birth; // 생일o
	private String year; // 년
	private String month; // 월
	private String day; // 일
	private String solarLunar; // 양력음력o
	private String business; // 사업자 인지 아닌지o
	private int businessNum; // 사업자일 경우 사업자 번호
	private String bussinessImage; // 사업자등록증 이미지

	public int getMemNo() {
		return memNo;
	}

	public void setMemNo(int memNo) {
		this.memNo = memNo;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPw() {
		return memPw;
	}

	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email1, String email2) {
		String email = email1 + "@" + email2;
		this.email = email;

	}

	public String getEmailReceive() {
		return emailReceive;
	}

	public void setEmailReceive(String emailReceive) {
		this.emailReceive = emailReceive;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address1, String address2) {
		this.address = address1 + "/ " + address2;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone1, String phone2, String phone3) {
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		this.phone = phone;
	}

	public String getSmsReceive() {
		return smsReceive;
	}

	public void setSmsReceive(String smsReceive) {
		this.smsReceive = smsReceive;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSolarLunar() {
		return solarLunar;
	}

	public void setSolarLunar(String solarLunar) {
		this.solarLunar = solarLunar;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public int getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}

	public String getBussinessImage() {
		return bussinessImage;
	}

	public void setBussinessImage(String bussinessImage) {
		this.bussinessImage = bussinessImage;
	}

}
