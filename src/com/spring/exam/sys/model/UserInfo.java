package com.spring.exam.sys.model;

import java.time.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class UserInfo {
	private String fullname;
	private String email;
	private String phone;
	private String address;

	@DateTimeFormat(pattern = "yyyy-MM-dd") // This is datetime Mysql-based format
	private LocalDate birthday;

	private String username;
	private String password;
	private String avata;
	private int role_id;
	private String role;
	private String feedback;

	public UserInfo() {
		super();
	}

	public UserInfo(String fullname, String email, String phone, String address, LocalDate birthday, String username,
			String password, String avata, int role_id, String role, String feedback) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
		this.avata = avata;
		this.role_id = role_id;
		this.role = role;
		this.feedback = feedback;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvata() {
		return avata;
	}

	public void setAvata(String avata) {
		this.avata = avata;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "UserInfo [fullname=" + fullname + ", email=" + email + ", phone=" + phone + ", address=" + address
				+ ", birthday=" + birthday + ", username=" + username + ", password=" + password + ", avata=" + avata
				+ ", role_id=" + role_id + ", role=" + role + ", feedback=" + feedback + "]";
	}

}
