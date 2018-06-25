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
	private HashMap<Integer, Restaurant> restaurants =new HashMap<Integer,Restaurant>();
	private HashMap<String, Vehicle> vehicles =new HashMap<String,Vehicle>();
	private HashMap<String, Order> orders =new HashMap<String,Order>();
	private HashMap<Integer, Article> articles= new HashMap<Integer,Article>();
	private ArrayList<RestArtic> resArtics= new ArrayList<RestArtic>();

	private String path;
	
	public Database() {
		super();
	}
	

	public Database(String path) {
		super();
		this.path = path;
		readUsers(path);
		readVehicle(path);
		readRestaurants(path);
		readArticles(path);
		readRestArtic(path);
	}



	public ArrayList<RestArtic> getResArtics() {
		return resArtics;
	}


	public void setResArtics(ArrayList<RestArtic> resArtics) {
		this.resArtics = resArtics;
	}


	public HashMap<Integer, Restaurant> getRestaurants() {
		return restaurants;
	}


	public void setRestaurants(HashMap<Integer, Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public HashMap<String, User> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, User> users) {
		this.users = users;
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

	public HashMap<Integer, Article> getArticles() {
		return articles;
	}


	public void setArticles(HashMap<Integer, Article> articles) {
		this.articles = articles;
	}

/////////////////////////////////////////////////////////////////////////////////
	
	
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
	public boolean restaurantExist(int id) {
		for(int key : restaurants.keySet()) {
			if(key==id);
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
		writeUsers(this.path);
		writeVehicles(this.path);
		writeRestaurants(this.path);
		writeArticles(this.path);
		writeResArtic(this.path);
	}
	

	@SuppressWarnings("unchecked")
	private void writeVehicles(String path) {
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		if(!getVehicles().isEmpty()) {
			for(Vehicle vehicle : vehicles.values()) {
				JSONObject object=new JSONObject();
				object.put("brand", vehicle.getBrand());
				object.put("model", vehicle.getModel());
				object.put("type", vehicle.getType().toString());
				object.put("register", vehicle.getRegister());
				object.put("note", vehicle.getNote());
				DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				object.put("yearOfProduction", dateFormat.format(vehicle.getYearOfProduction()));
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
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void writeUsers(String path) {
	
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		for(User user : users.values()) {
			JSONObject object = new JSONObject();
			object.put("username", user.getUsername());
			object.put("password",user.getPassword());
			object.put("name", user.getName());
			object.put("surname", user.getSurname());
//			DateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			object.put("dateOfRegistration", dateFormat.format(user.getDateOfRegistration()));
			if(user.getRole().toString()!=null)
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
	public void writeRestaurants(String path) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(!restaurants.isEmpty()) {
			for(Restaurant restaurant : restaurants.values()) {
				JSONObject object = new JSONObject();
				object.put("id",restaurant.getId());
				object.put("name",restaurant.getName());
				object.put("adress", restaurant.getAdress());
				object.put("category", restaurant.getCategory().toString());
				array.add(object);
			}

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
	

	@SuppressWarnings("unchecked")
	private void writeArticles(String path2) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(!articles.isEmpty()) {
			for(Article article: articles.values()) {
				JSONObject object = new JSONObject();
				object.put("id", article.getId());
				object.put("name", article.getName());
				object.put("price", article.getPrice());
				object.put("description",article.getDescription());
				//object.put("artImgPath", article.get);
				object.put("amount", article.getAmount());
				object.put("typeOfArticle", article.getTypeOfArticle());
				array.add(object);
			}
			obj.put("articles",array);
			FileWriter file;
			try {
				file = new FileWriter(path+"dummyFiles/articles.json");
				file.write(obj.toString());
			    file.flush();
			    file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void writeResArtic(String path2) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(!resArtics.isEmpty()) {
			for(RestArtic restArtic: resArtics) {
				JSONObject object = new JSONObject();
				object.put("idR", restArtic.getIdR());
				object.put("idA", restArtic.getIdA());
				array.add(object);
			}	
			obj.put("restArtic",array);
			FileWriter file;
			try {
				file = new FileWriter(path+"dummyFiles/restArtic.json");
				file.write(obj.toString());
			    file.flush();
			    file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
				User user = new User(username, password,name,surname,role2,phone,email);
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
	
	
	private void readRestaurants(String path2) {
		JSONParser parser = new JSONParser();
		  try {
			Object obj = parser.parse(new FileReader(path2 + "dummyFiles/restaurants.json"));
			JSONObject jsonObject = (JSONObject) obj;
	        JSONArray restaurants1 = (JSONArray) jsonObject.get("restaurants");
			String name = "",adress= "",category="";
			long id=0;
			
			for (Object restaurant1 : restaurants1) { 
				JSONObject restaurant2 = (JSONObject) restaurant1;
				id= (long) restaurant2.get("id");
				name = (String) restaurant2.get("name");
				adress = (String) restaurant2.get("adress");
				category=(String) restaurant2.get("category");
				Category category2=Category.valueOf(category);
				
				Restaurant restaurant =new Restaurant((int)id,name, adress, category2);
					if(!restaurants.containsKey((int)id)) {
						restaurants.put((int)id, restaurant);
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
				for (Object vehicle1: vehicles1) { 
					JSONObject vehicle2= (JSONObject) vehicle1;
					brand=(String) vehicle2.get("brand");
					model=(String) vehicle2.get("model");
					register=(String) vehicle2.get("register");
					inUse = (boolean) vehicle2.get("inUse");
					type=(String) vehicle2.get("type");
					Type type2=Type.valueOf(type);
					yearOfProduction=(String) vehicle2.get("yearOfProduction");
					Vehicle vehicle=new Vehicle(brand, model, type2, register, convertStringToDate(yearOfProduction), inUse, note);
					if(!vehicles.containsKey(vehicle.getRegister()))
						vehicles.put(vehicle.getRegister(), vehicle);
				}
				
				
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}		
	}	
	private void readArticles(String path2) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(path + "dummyFiles/articles.json"));
			JSONObject jsonObject = (JSONObject) obj;
	        JSONArray articles1 = (JSONArray) jsonObject.get("articles");
	        long id=0;double price=0.0;double amount=0.0;TypeOfArticle typeOfArticle;String typeOfArticle2="";
	        String name="";String description="";
	        for (Object article1: articles1) { 
	        	JSONObject article2= (JSONObject) article1;
	        	id=(long) article2.get("id");
	        	name=(String) article2.get("name");
	        	price=(double) article2.get("price");
	        	description=(String) article2.get("description");
	        	amount=(double)article2.get("amount");
	        	typeOfArticle2=(String) article2.get("typeOfArticle");
	        	typeOfArticle=TypeOfArticle.valueOf(typeOfArticle2);
	        	Article article = new Article((int)id, name, price, description, amount, typeOfArticle);
	        	if(!articles.containsKey(article.getId()))
	        		articles.put(article.getId(),article);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void readRestArtic(String path2) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(path + "dummyFiles/restArtic.json"));
			JSONObject jsonObject = (JSONObject) obj;
	        JSONArray restArtics1 = (JSONArray) jsonObject.get("restArtic");
	        long idR=0; long idA=0;
	        for (Object restArtic1: restArtics1) { 
	        	JSONObject restArtic2= (JSONObject) restArtic1;
	        	idR= (long)restArtic2.get("idR");
	        	idA= (long)restArtic2.get("idA");
	        	RestArtic restArtic= new RestArtic((int)idR,(int) idA);
		        	resArtics.add(restArtic);
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Article> readArticlesFromRestaurant(int id) {
		JSONParser parser = new JSONParser();
		Object obj1,obj2;
		ArrayList<Article> listOfArticles= new ArrayList<Article>();
		HashMap<Integer, Integer> idsForArticle= new HashMap<Integer, Integer>();
		try {
			obj1 = parser.parse(new FileReader(path + "dummyFiles/restArtic.json"));
			obj2 = parser.parse(new FileReader(path + "dummyFiles/articles.json"));
			JSONObject jsonObject1 = (JSONObject) obj1;
			JSONObject jsonObject2 = (JSONObject) obj2;
	        JSONArray restArtics1 = (JSONArray) jsonObject1.get("restArtic");
	        JSONArray articles1 = (JSONArray) jsonObject2.get("articles");
	        long idR,idA;
	        String name,description,artImgPath,typeOfArticle2; double price,amount; TypeOfArticle typeOfArticle;
		        for (Object restArtic1: restArtics1) { 
		        	JSONObject restArtic2= (JSONObject) restArtic1;
		        	idR= (long)restArtic2.get("idR");
		        	idA= (long)restArtic2.get("idA");
		        	if(id==(int)idR) {
		        		idsForArticle.put((int)idR,(int) idA);
		        }
		        for(Object article1 : articles1) {
		        	JSONObject article2= (JSONObject) article1;
		        		idA=(long)article2.get("id");
		        		for(int idAvalue : idsForArticle.values()) {
		        				if((int)idA==idAvalue) {
		        					name=(String)article2.get("name");
		        					price= (double) article2.get("price");
		        					description=(String)article2.get("description");
		        				//	artImgPath=(String)article2.get("artImgPath");
		        					amount=(double)article2.get("amount");
		        					typeOfArticle2=(String)article2.get("typeOfArticle");
		        					typeOfArticle=TypeOfArticle.valueOf(typeOfArticle2);
		        					Article article= new Article((int)idA, name, price, description, amount, typeOfArticle);
		        					listOfArticles.add(article);
		        				}
		        		}
		        }
	        } 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 return listOfArticles;
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

