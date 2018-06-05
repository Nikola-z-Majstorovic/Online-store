package model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Database {

	HashMap<String, User> users;
	HashMap<String, Article> atricles;
	HashMap<String, Restaurant> restaurants;
	HashMap<String, Vehicle> vehicles;
	HashMap<String, Order> orders;
	String path;
	
	public Database() {
		super();
	}
	
	public Database(String path) {
		BufferedReader in = null;
		this.path = path;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	public HashMap<String, Article> getAtricles() {
		return atricles;
	}

	public void setAtricles(HashMap<String, Article> atricles) {
		this.atricles = atricles;
	}

	public HashMap<String, Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(HashMap<String, Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public HashMap<String, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}
	public Collection<User> getUsersValues() {
		return users.values();
	}
	
	public boolean usernameExists(String username) {
		for(String key : users.keySet()) {
			if(key.equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}
	public boolean emailExists(String email) {
		for(User user : users.values()) {
			if((user.getEmail()).equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	public void writeData() {
		
		PrintWriter out = null;
		try {
			writeCustomer(this.path);

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( out != null ) {
				try {
					out.close();
				}
				catch (Exception e) { }
			}
		}
		
	}
	private void writeCustomer(String path) {
		System.out.println("usao");
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		for(User user : getUsersValues()) {
			JSONObject object = new JSONObject();
			object.put("username", user.getUsername());
			object.put("password",user.getPassword());
			object.put("name", user.getName());
			object.put("surname", user.getSurname());
			object.put("phone", user.getPhone());
			object.put("email", user.getEmail());
			array.add(object);
		}
		
		try {
			obj.put("users", array);
		    FileWriter file = new FileWriter(path+"dummyFiles/users.json");
		    file.write(obj.toString());
		    file.flush();
		    file.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	public boolean loginCheck(User user) {
		for(User user2 : users.values()) {
			if((user2.getUsername()).equalsIgnoreCase(user.getUsername())) {
				if(user2.getPassword().equalsIgnoreCase(user.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
}
