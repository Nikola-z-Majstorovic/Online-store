package model;

import java.util.Arrays;

public class User{


	private int id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Role role;
	private String phone;
	private String email;
	private String dateOfRegistration;
	private String register;
	private String myFavoriteRestaurants[];

	public User()   {
		super();
	}




	public User(int id, String username, String password, String name, String surname, Role role, String phone,
			String email, String dateOfRegistration, String register, String[] myFavoriteRestaurants) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.dateOfRegistration = dateOfRegistration;
		this.register = register;
		this.myFavoriteRestaurants = myFavoriteRestaurants;
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
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", surname="
				+ surname + ", role=" + role + ", phone=" + phone + ", email=" + email + ", dateOfRegistration="
				+ dateOfRegistration + ", register=" + register + ", myFavoriteRestaurants="
				+ Arrays.toString(myFavoriteRestaurants) + "]";
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getRegister() {
		return register;
	}


	public void setRegister(String register) {
		this.register = register;
	}


	public String getDateOfRegistration() {
		return dateOfRegistration;
	}


	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}


	public String[] getMyFavoriteRestaurants() {
		return myFavoriteRestaurants;
	}


	public void setMyFavoriteRestaurants(String myFavoriteRestaurants[]) {
		this.myFavoriteRestaurants = myFavoriteRestaurants;
	}




	
}
