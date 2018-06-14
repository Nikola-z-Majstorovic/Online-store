package model;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Role role;
	private String phone;
	private String email;
	private Date dateOfRegistration;
	


	public User() {
		super();
	}

	public User(String username,String password, String name,String surname, Role role,Date dateOfRegistration,String phone, String email) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.email = email;
		this.surname=surname;
		this.role=role;
		this.dateOfRegistration = dateOfRegistration;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

//	public Date getDateOfRegistration() {
//		return dateOfRegistration;
//	}
//
//	public void setDateOfRegistration(Date dateOfRegistration) {
//		this.dateOfRegistration = dateOfRegistration;
//	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", name=" + name + ", surname=" + surname
				+ ", role=" + role + ", phone=" + phone + ", email=" + email + ", dateOfRegistration="
				+ dateOfRegistration + "]";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(Date dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
}
