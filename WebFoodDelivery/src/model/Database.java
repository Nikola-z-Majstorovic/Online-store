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

	HashMap<String, Customer> customers;
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

	public HashMap<String, Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(HashMap<String, Customer> customers) {
		this.customers = customers;
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
	
	public Collection<Customer> getCustomerValues() {
		return customers.values();
	}
	
	public boolean usernameExists(String username) {
		for(String key : customers.keySet()) {
			if(key.equalsIgnoreCase(username)) {
				return true;
			}
		}
		return false;
	}
	public boolean emailExists(String email) {
		for(Customer customer : customers.values()) {
			if((customer.getEmail()).equalsIgnoreCase(email)) {
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
		
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		for(Customer customer : getCustomerValues()) {
			JSONObject object = new JSONObject();
			object.put("username", customer.getUsername());
			object.put("password",customer.getPassword());
			object.put("name", customer.getName());
			object.put("surname", customer.getSurname());
			object.put("phone", customer.getPhone());
			object.put("email", customer.getEmail());
			array.add(object);
		}
		
		try {
			obj.put("customers", array);
		    FileWriter file = new FileWriter(path+"dummyFiles/customers.json");
		    file.write(obj.toString());
		    file.flush();
		    file.close();
		    
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
}
