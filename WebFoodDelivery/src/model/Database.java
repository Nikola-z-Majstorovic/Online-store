package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Database {

	private HashMap<String, User> users = new HashMap<String, User>();
	private HashMap<String, Restaurant> restaurants =new HashMap<String,Restaurant>();
	private HashMap<String, Vehicle> vehicles =new HashMap<String,Vehicle>();
	private HashMap<String, Order> orders =new HashMap<String,Order>();
	private String path;
	
	public Database() {
		super();
	}
	

	public Database(String path) {
		super();
		this.path = path;
		readUsers(path);
		readRestaurants(path);
		readVehicle(path);
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
	}

	public HashMap<String, Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(HashMap<String, Restaurant> restaurants) {
		this.restaurants = restaurants;
	}


	public HashMap<String, Order> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<String, Order> orders) {
		this.orders = orders;
	}
	
	public HashMap<String, Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(HashMap<String, Vehicle> vehicles) {
		this.vehicles = vehicles;
	}


	//////////////////////////////////////////////////////////////////////////////////
	
	
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
	public boolean restaurantExist(String name) {
		for(String key : restaurants.keySet()) {
			if(key.equalsIgnoreCase(name));
			return true;
		}
		return false;
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
	public boolean vehicleExist(String register) {
		for(String key : vehicles.keySet()) {
			if(key.equalsIgnoreCase(register)) 
				return true;
		}
		return false;
	}
		
	public void writeData() {
		writeUser(this.path);
		writeRestaurant(this.path);
		writeVehicle(this.path);
	}
	
	@SuppressWarnings("unchecked")
	private void writeVehicle(String path) {
		// TODO Auto-generated method stub
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Vehicle vehicle : vehicles.values()) {
			JSONObject object=new JSONObject();
			object.put("brand", vehicle.getBrand());
			object.put("model", vehicle.getModel());
			object.put("type", vehicle.getType().toString());
			object.put("register", vehicle.getRegister());
			object.put("note", vehicle.getNote());
			DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			object.put("yearOfProduction", dateFormat);
			object.put("inUse", vehicle.isInUse());
			array.add(object);
		}
		obj.put("vehicles", array);
		try {
			FileWriter file = new FileWriter(path+"dummyFiles/vehicles.json");
			file.write(obj.toString());
			file.flush();
			file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void writeUser(String path) {
	
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		for(User user : users.values()) {
			JSONObject object = new JSONObject();
			object.put("username", user.getUsername());
			object.put("password",user.getPassword());
			object.put("name", user.getName());
			object.put("surname", user.getSurname());
			DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			object.put("dateOfRegistration", dateFormat);
			object.put("role", user.getRole().toString());
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
	
	@SuppressWarnings("unchecked")
	public void writeRestaurant(String path) {
		JSONArray array = new JSONArray();
		JSONArray arrayFoods = new JSONArray();
		JSONArray arrayDrinks= new JSONArray();
		JSONObject obj = new JSONObject();
		for(Restaurant restaurant : restaurants.values()) {
			JSONObject object = new JSONObject();
			object.put("name",restaurant.getName());
			object.put("adress", restaurant.getAdress());
			object.put("category", restaurant.getCategory().toString());
			
			if(restaurant.getFoods()!=null) {
				for(Article food : restaurant.getFoods()) {
					JSONObject objectFood= new JSONObject();
					objectFood.put("id", food.getId());
					objectFood.put("name", food.getName());
					objectFood.put("price", food.getPrice());
					objectFood.put("description", food.getDescription());
					objectFood.put("amount", food.getAmount());
					arrayFoods.add(objectFood);
				}
			}
			object.put("foods", arrayFoods);
			if(restaurant.getDrinks()!=null) {
				for(Article food : restaurant.getFoods()) {
					JSONObject objectDrinks= new JSONObject();
					objectDrinks.put("id", food.getId());
					objectDrinks.put("name", food.getName());
					objectDrinks.put("price", food.getPrice());
					objectDrinks.put("description", food.getDescription());
					objectDrinks.put("amount", food.getAmount());
					arrayDrinks.add(objectDrinks);
				}
			}
			object.put("drinks", arrayDrinks);
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
			e.printStackTrace();
		}

	}
	public void readUsers(String path) {
		JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path + "dummyFiles/users.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray users1 = (JSONArray) jsonObject.get("users");
            
            String username = "", password = "", name = "", surname = "",
    				phone = "", email = "",role="",dateOfRegistration="";

            for (Object user1 : users1) {
            	JSONObject user2 = (JSONObject) user1;
				username= (String) user2.get("username");
				password= (String) user2.get("password");
				name= (String) user2.get("name");
				surname= (String) user2.get("surname");
				role=(String) user2.get("role");
				Role role2=Role.valueOf(role);
				dateOfRegistration=(String) user2.get("dateOfRegistration");
				phone= (String) user2.get("phone");
				email= (String) user2.get("email");
				User user = new User(username, password, name, surname,role2,convertStringToDate(dateOfRegistration), phone, email);
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
	private void readRestaurants(String path2) {
		JSONParser parser = new JSONParser();
		  try {
			Object obj = parser.parse(new FileReader(path + "dummyFiles/users.json"));
			JSONObject jsonObject = (JSONObject) obj;
	        JSONArray restaurants1 = (JSONArray) jsonObject.get("restaurants");
			String name = "",adress= "",category="";
			
			for (Object restaurant1 : restaurants1) { 
				JSONObject restaurant2 = (JSONObject) restaurant1;
				name = (String) restaurant2.get("name");
				adress = (String) restaurant2.get("adress");
				category=(String) restaurant2.get("category");
				Category category2=Category.valueOf(category);

				JSONArray foods= (JSONArray) restaurant2.get("foods");
				ArrayList<Article> listFoods= new ArrayList<Article>();
				
				String nameOfFood="",description="";
				int id=0;int price=0;float amount =0;

				if(foods!=null) {
					for(Object food :foods) {
						JSONObject food1= (JSONObject) food;
						id=(int) food1.get("id");
						nameOfFood=(String) food1.get("name");
						description=(String) food1.get("description");
						amount=(float) food1.get("amount");
						price=(int) food1.get("price");
						Article foodArticle= new Article(id, nameOfFood, price, description, amount);
						listFoods.add(foodArticle);
					}
			
				}
				foods.add(listFoods);
				JSONArray drinks= (JSONArray) restaurant2.get("drinks");
				ArrayList<Article> listDrinks= new ArrayList<Article>();
				if(drinks!=null) {
					for(Object drink :drinks) {
						JSONObject drink1= (JSONObject) drink;
						id=(int) drink1.get("id");
						nameOfFood=(String) drink1.get("name");
						description=(String) drink1.get("description");
						amount=(float) drink1.get("amount");
						price=(int) drink1.get("price");
						Article drinkArticle= new Article(id, nameOfFood, price, description, amount);
						listDrinks.add(drinkArticle);
					}
				}
				drinks.add(listDrinks);
				Restaurant restaurant =new Restaurant(name, adress, category2, foods, drinks);
				if(!restaurants.containsKey(name)) {
					restaurants.put(name, restaurant);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		  
	}
	

	private void readVehicle(String path) {
		JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(new FileReader(path + "dummyFiles/vehicles.json"));
				JSONObject jsonObject = (JSONObject) obj;
		        JSONArray vehicles1 = (JSONArray) jsonObject.get("vehicles");
				String brand = "",model= "",register="",note="",type="",yearOfProduction="";
				boolean inUse;
				for (Object vehicle1: vehicles.values()) { 
					JSONObject vehicle2= (JSONObject) vehicle1;
					brand=(String) vehicle2.get("brand");
					model=(String) vehicle2.get("model");
					register=(String) vehicle2.get("register");
					note=(String) vehicle2.get("note");
					inUse=(boolean)vehicle2.get("inUse");
					type=(String) vehicle2.get("type");
					Type type2=Type.valueOf(type);
					yearOfProduction=(String) vehicle2.get("yearOfProduction");
					Vehicle vehicle=new Vehicle(brand, model, type2, register, convertStringToDate(yearOfProduction), inUse, note);
					if(!vehicles.containsKey(vehicle.getRegister()))
						vehicles.put(vehicle.getRegister(), vehicle);
				}
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

	private Date convertStringToDate(String dateString)
	{
	    Date date = null;
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try{
	        date = df.parse(dateString);
	    }
	    catch ( Exception ex ){
	        System.out.println(ex);
	    }
	    return date;
	}

	
}

