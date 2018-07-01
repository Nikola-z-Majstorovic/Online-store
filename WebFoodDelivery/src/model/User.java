package model;

public class User{


	private int id;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Role role;
	private String phone;
	private String email;
//	private Date dateOfRegistration;
	private String register;
	
	public User()   {
		super();
	}


	public User(int id, String username, String password, String name, String surname, Role role, String phone,
			String email, String register) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.phone = phone;
		this.email = email;
		this.setRegister(register);
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
				+ surname + ", role=" + role + ", phone=" + phone + ", email=" + email + ", register=" + register + "]";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

//	public Date getDateOfRegistration() {
//		return dateOfRegistration;
//	}
//
//	public void setDateOfRegistration(Date dateOfRegistration) {
//		this.dateOfRegistration = dateOfRegistration;
//	}
//
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




	
}
