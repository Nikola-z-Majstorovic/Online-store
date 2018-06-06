package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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
	
	@SuppressWarnings("unused")
	public Database(String path) {
		BufferedReader in = null;
		this.path = path;
		readUsers(path);
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
	////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	private Collection<Restaurant> getRestaurantsValues() {
		return restaurants.values();
		
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
			writeUser(this.path);
			writeRestaurant(this.path);
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
	
	 
	@SuppressWarnings("unchecked")
	private void writeRestaurant(String path) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		for(Restaurant restaurant : getRestaurantsValues()) {
			JSONObject object = new JSONObject();
			object.put("name",restaurant.getName());
			object.put("adress", restaurant.getAdress());
			object.put("category", restaurant.getCategory());
			array.add(object);
		}
		obj.put("restaurants",array);
		FileWriter file;
		try {
			file = new FileWriter(path+"dummyFiles/restaurants.json");
		    file.write(obj.toString());
		    file.flush();
		    file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void readUsers(String path) {
		JSONParser parser = new JSONParser();
		

        try {

            Object obj = parser.parse(new FileReader(path + "dummyFiles/users.json"));

            JSONObject jsonObject = (JSONObject) obj;
            JSONArray users1 = (JSONArray) jsonObject.get("users");
            
            String username = "", password = "", name = "", surname = "",
    				phone = "", email = "";

            for (Object user1 : users1) {
            	JSONObject user2 = (JSONObject) user1;
				User user = new User(username, password, name, surname, phone, email);
				username= (String) user2.get("username");
				password= (String) user2.get("password");
				name= (String) user2.get("name");
				surname= (String) user2.get("surname");
				phone= (String) user2.get("phone");
				email= (String) user2.get("email");
				if(!users.containsKey(username)) 
					users.put(username, user);
            }
            

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
	}
	


	@SuppressWarnings("unchecked")
	private void writeUser(String path) {
	
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
		for(User value : users.values()) {
			if((value.getUsername()).equalsIgnoreCase(user.getUsername())) {
				if(value.getPassword().equalsIgnoreCase(user.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean restaurantExist(String name) {
		for(String key : restaurants.keySet()) {
			if(key.equalsIgnoreCase(name));
			return true;
		}
		return false;
	}
	
}

