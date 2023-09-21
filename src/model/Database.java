package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Database {

	private HashMap<Integer, User> users = new HashMap<Integer, User>();
	private HashMap<Integer, Restaurant> restaurants =new HashMap<Integer,Restaurant>();
	private HashMap<String, Vehicle> vehicles =new HashMap<String,Vehicle>();
	private HashMap<Integer, Order> orders =new HashMap<Integer,Order>();
	private HashMap<Integer, Article> articles= new HashMap<Integer,Article>();

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
		readOrders(path);
	}


	public HashMap<Integer, Restaurant> getRestaurants() {
		return restaurants;
	}


	public void setRestaurants(HashMap<Integer, Restaurant> restaurants) {
		this.restaurants = restaurants;
	}


	
	public HashMap<Integer, User> getUsers() {
		return users;
	}


	public void setUsers(HashMap<Integer, User> users) {
		this.users = users;
	}


	public HashMap<Integer, Order> getOrders() {
		return orders;
	}


	public void setOrders(HashMap<Integer, Order> orders) {
		this.orders = orders;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
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
	for(User user : users.values()) {
			if(user.getName().equalsIgnoreCase(username)) {
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
			if(key==id)
			return true;
		}
		return false;
	}
	public boolean articleExist(int id) {
		for(int key : articles.keySet()) {
			if(key==id)
			return true;
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
	public boolean orderExist(int id) {
		for(int key : orders.keySet()) {
			if(key==id) 
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

		
	public void writeData() {
		writeUsers(this.path);
		writeVehicles(this.path);
		writeRestaurants(this.path);
		writeArticles(this.path);
		writeOrders(this.path);
	}
	

	@SuppressWarnings("unchecked")
	private void writeUsers(String path) {
	
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();

		for(User user : users.values()) {
			JSONObject object = new JSONObject();
			object.put("id", user.getId());
			object.put("username", user.getUsername());
			object.put("password",user.getPassword());
			object.put("name", user.getName());
			object.put("surname", user.getSurname());
			String timeStamp = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(Calendar.getInstance().getTime());
			object.put("dateOfRegistration",user.getDateOfRegistration());
			if(user.getRole().toString()!=null)
			object.put("role", user.getRole().toString());
			object.put("phone", user.getPhone());
			object.put("email", user.getEmail());
			object.put("register",user.getRegister());
			object.put("myFavoriteRestaurants", user.getMyFavoriteRestaurants());
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
//				String timeStamp = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(Calendar.getInstance().getTime());
				object.put("yearOfProduction", vehicle.getYearOfProduction());
				object.put("inUse", vehicle.isInUse());
				object.put("note", vehicle.getNote());
				object.put("visibility",vehicle.getVisibility());
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
	private void writeRestaurants(String path) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(!restaurants.isEmpty()) {
			for(Restaurant restaurant : restaurants.values()) {
				JSONObject object = new JSONObject();
				object.put("id",restaurant.getId());
				object.put("name",restaurant.getName());
				object.put("adress", restaurant.getAdress());
				object.put("category", restaurant.getCategory().toString());
				object.put("visibility",restaurant.getVisibility());
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
	private void writeArticles(String path) {
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		if(!articles.isEmpty()) {
			for(Article article: articles.values()) {
				JSONObject object = new JSONObject();
				object.put("id", article.getId());
				object.put("name", article.getName());
				object.put("price", article.getPrice());
				object.put("description",article.getDescription());
				object.put("artImgPath", article.getArtImgPath());
				object.put("quantity", article.getQuantity());
				object.put("isFood", article.isFood());
				object.put("idR",article.getIdR());
				object.put("visibility", article.getVisibility());
				object.put("articleRanking", article.getArticleRanking());
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
	private void writeOrders(String path) {
		JSONObject obj=new JSONObject();
		JSONArray array = new JSONArray();
		if(!orders.isEmpty()) {
			for(Order order: orders.values()) {
				JSONObject object = new JSONObject();
				object.put("id", order.getId());
//				String timeStamp = new SimpleDateFormat("yyyy:MM:dd-HH:mm:ss").format(Calendar.getInstance().getTime());
				object.put("dateOfOrder",order.getDateOfOrder());
				object.put("idC", order.getIdC());
				object.put("idD",order.getIdD());
				object.put("idR", order.getIdR());
				object.put("status", order.getStatus().toString());
				object.put("note",order.getNote());
				object.put("visibility",order.isVisibility());
				object.put("price",order.getPrice());
				ArrayList<ArticleOrders> articleOrders= order.getArticleOrders();
				JSONArray arrayOrders = new JSONArray();
				for(ArticleOrders articleOrder : articleOrders) {
					JSONObject objArtOrd = new JSONObject();

					objArtOrd.put("idA", articleOrder.getIdA());
					objArtOrd.put("amount", articleOrder.getAmount());
					objArtOrd.put("name",articleOrder.getName());
					objArtOrd.put("priceByUnit",articleOrder.getPriceByUnit());
					arrayOrders.add(objArtOrd);
				}
				object.put("articleOrders", arrayOrders);
				array.add(object);		
				}
			obj.put("orders",array);
		}
	    try {
			FileWriter file = new FileWriter(path+"dummyFiles/orders.json");			
		    file.write(obj.toString());
		    file.flush();
		    file.close();
		} catch (IOException e) {
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
    				phone = "", email = "",role="",register="",dateOfRegistration;
            long id;String[] numbers = null;
            if(users1!=null) {
	            for (Object user1 : users1) {
	            	JSONObject user2 = (JSONObject) user1;
	            	id=(long) user2.get("id");
					username= (String) user2.get("username");
					password= (String) user2.get("password");
					name= (String) user2.get("name");
					surname= (String) user2.get("surname");
					role=(String) user2.get("role");
					Role role2=Role.valueOf(role);
					dateOfRegistration=(String) user2.get("dateOfRegistration");
					phone= (String) user2.get("phone");
					email= (String) user2.get("email");
					register = (String) user2.get("register");
		            JSONArray favoriteRestaurants = (JSONArray) jsonObject.get("myFavoriteRestaurants");

		            if(favoriteRestaurants != null) {
		            	if(favoriteRestaurants.size() > 0) {
		            		numbers = new String[favoriteRestaurants.size()];
			            	for(int i=0;i<favoriteRestaurants.size();i++) {
				          		numbers[i]=	(String) favoriteRestaurants.get(i);
				            }
		            	}
		            }
		    
					User user = new User((int)id, username, password, name, surname, role2, phone, email, dateOfRegistration,register,numbers);
	
					if(!users.containsKey((int)id)); 
						users.put((int)id, user);
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
	
	
	private void readRestaurants(String path2) {
		JSONParser parser = new JSONParser();
		  try {
			Object obj = parser.parse(new FileReader(path2 + "dummyFiles/restaurants.json"));
			JSONObject jsonObject = (JSONObject) obj;
	        JSONArray restaurants1 = (JSONArray) jsonObject.get("restaurants");
			String name = "",adress= "",category="";
			boolean visibility;
			long id=0;
			if(restaurants1!=null) {
				for (Object restaurant1 : restaurants1) { 
					JSONObject restaurant2 = (JSONObject) restaurant1;
					visibility= (boolean) restaurant2.get("visibility");
						id= (long) restaurant2.get("id");
						name = (String) restaurant2.get("name");
						adress = (String) restaurant2.get("adress");
						category=(String) restaurant2.get("category");
						Category category2=Category.valueOf(category);
		
						Restaurant restaurant =new Restaurant((int) id,name, adress, category2,visibility);
							if(!restaurants.containsKey((int)id)) {
								restaurants.put((int)id, restaurant);
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
	}
	

	private void readVehicle(String path) {
		JSONParser parser = new JSONParser();
			Object obj;
			try {
				obj = parser.parse(new FileReader(path + "dummyFiles/vehicles.json"));
				JSONObject jsonObject = (JSONObject) obj;
		        JSONArray vehicles1 = (JSONArray) jsonObject.get("vehicles");
				String brand = "",model= "",register="",note="",type="",yearOfProduction;
				boolean inUse;boolean visibility;
				if(vehicles1!=null) {
					for (Object vehicle1: vehicles1) { 
						JSONObject vehicle2= (JSONObject) vehicle1;
						visibility= (boolean) vehicle2.get("visibility");
						brand=(String) vehicle2.get("brand");
						model=(String) vehicle2.get("model");
						register=(String) vehicle2.get("register");
						inUse = (boolean) vehicle2.get("inUse");
						type=(String) vehicle2.get("type");
						Type type2=Type.valueOf(type);
						yearOfProduction=(String) vehicle2.get("yearOfProduction");
						note= (String) vehicle2.get("note");
						Vehicle vehicle=new Vehicle(brand, model, type2, register,yearOfProduction, inUse, note,visibility);
						if(!vehicles.containsKey(vehicle.getRegister()))
							vehicles.put(vehicle.getRegister(), vehicle);
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
	private void readOrders(String path) {
		JSONParser parser = new JSONParser();
		
			try {
				Object obj = parser.parse(new FileReader(path + "dummyFiles/orders.json"));
				JSONObject jsonObject = (JSONObject) obj;
		        JSONArray orders1 = (JSONArray) jsonObject.get("orders");
		        boolean visibility; long id,idC,idR,idA,amount; String status2,note,idD; Status status;
		        String name,dateOfOrder;double priceByUnit,price; 
				if(orders1!=null) {
			        for (Object order1: orders1) { 
					JSONObject order2= (JSONObject) order1;
					visibility=(boolean)order2.get("visibility");
						id= (long) order2.get("id");
						dateOfOrder=(String) order2.get("dateOfOrder");
						idC=(long) order2.get("idC");						
						idD=(String) order2.get("idD");
						status2=(String) order2.get("status");
						status=Status.valueOf(status2);
						idR=(long)order2.get("idR");
						note=(String) order2.get("note");
						price=(double)order2.get("price");
						ArrayList<ArticleOrders> articleOrders= new ArrayList<ArticleOrders>();
				        JSONArray articleOrders1 = (JSONArray) order2.get("articleOrders");
							for(Object articleOrder1 : articleOrders1) {
								JSONObject articleOrder2= (JSONObject) articleOrder1;
								idA=(long)articleOrder2.get("idA");
								amount=(long)articleOrder2.get("amount");
								name=(String) articleOrder2.get("name");
								priceByUnit=(double) articleOrder2.get("priceByUnit");
								ArticleOrders articleOrders2= new ArticleOrders((int)idA,(int)amount,name,priceByUnit);
								articleOrders.add(articleOrders2);
							}
						Order order = new Order((int)id, dateOfOrder,(int)idC, idD, (int)idR, status, note, articleOrders, visibility,price);
						orders.put((int)id, order);											
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


	private void readArticles(String path) {
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(new FileReader(path + "dummyFiles/articles.json"));
			JSONObject jsonObject = (JSONObject) obj;
	        JSONArray articles1 = (JSONArray) jsonObject.get("articles");
	        boolean visibility,isFood;long id,articleRanking,idR;String name,description,artImgPath,quantity;double price;
		    if(articles1!=null) {
	        	for (Object article1: articles1) { 
		        	JSONObject article2= (JSONObject) article1;
		        	visibility=(boolean) article2.get("visibility");
		        		id= (long) article2.get("id");
		        		name= (String) article2.get("name");
		        		price=(double) article2.get("price");
		        		description= (String) article2.get("description");
		        		artImgPath=(String) article2.get("artImgPath");
		        		quantity= (String) article2.get("quantity");
		        		isFood=(boolean) article2.get("isFood");
		        		idR= (long) article2.get("idR");
		        		articleRanking=(long) article2.get("articleRanking");
		        		Article article = new Article((int)id, name, price, description, artImgPath, quantity, isFood, visibility,(int) idR,(int)articleRanking);
		        		articles.put((int)id, article);
		        	
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
	public ArrayList<Article> readArticlesFromRestaurant(int id) {
		JSONParser parser = new JSONParser();
		Object obj1;
		ArrayList<Article> listOfArticles= new ArrayList<Article>();
		try {
			obj1 = parser.parse(new FileReader(path + "dummyFiles/articles.json"));
			JSONObject jsonObject1 = (JSONObject) obj1;
			 JSONArray articles1 = (JSONArray) jsonObject1.get("articles");
			 long idA;String name="",description="",artImgPath="",quantity="";double price; boolean visibility,isFood;long idR,articleRanking;
			 if(articles1!=null) {
			 for (Object article1: articles1) {
		        JSONObject article2= (JSONObject) article1;
		        idR=(long)article2.get("idR");
		        if(idR==id) {
		        	visibility= (boolean) article2.get("visibility");
		        	idA= (long) article2.get("id");
		        	name=(String) article2.get("name");
		        	price=(double) article2.get("price");
		        	description= (String) article2.get("description");
		        	artImgPath=(String) article2.get("artImgPath");
		        	quantity= (String) article2.get("quantity");
		        	isFood=(boolean) article2.get("isFood");
		        	idR= (long) article2.get("idR");
		        	articleRanking= (long) article2.get("articleRanking");
		        	Article article =  new Article((int)idA, name, price, description,artImgPath,quantity, isFood, visibility, (int)idR,(int)articleRanking);
		        	listOfArticles.add(article);
		        	
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

	public ArrayList<Restaurant> readRestaurantFromArticle(int id) {
		JSONParser parser = new JSONParser();
		Object obj1,obj2;
		ArrayList<Restaurant> listOfRestaurants=new ArrayList<Restaurant>();
		try {
			obj1 = parser.parse(new FileReader(path + "dummyFiles/articles.json"));
			obj2 = parser.parse(new FileReader(path + "dummyFiles/restaurants.json"));
			JSONObject jsonObject1 = (JSONObject) obj1;
			JSONObject jsonObject2 = (JSONObject) obj2;
			JSONArray articles1 = (JSONArray) jsonObject1.get("articles");
			JSONArray restaurants1 = (JSONArray) jsonObject2.get("restaurants");
			long idR;String name,adress,category2;boolean visibility;Category category;
			if(articles1!=null) {
				for (Object article1: articles1) {
				    JSONObject article2= (JSONObject) article1;
				    idR=(long) article2.get("idR");
				    if(idR==id) {
				    	if(restaurants1!=null) {
				    	for(Object restaurant1 : restaurants1) {
						    JSONObject restaurant2= (JSONObject) restaurant1;
						    idR=(long) restaurant2.get("id");
						    if(idR==id) {
						    	name= (String) restaurant2.get("name");
						    	adress= (String) restaurant2.get("adress");
						    	category2= (String) restaurant2.get("category");
						    	category= Category.valueOf(category2);
						    	visibility = (boolean) restaurant2.get("visibility");
						    	Restaurant restaurant = new Restaurant((int)id,name, adress, category, visibility);
						    	listOfRestaurants.add(restaurant);
						    }
				    	}
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
		return listOfRestaurants;
	}
	public void deleteRestaurant(int id) {
		if(restaurantExist(id)) {
			for(Restaurant restaurant: restaurants.values()) {
				if(restaurant.getId()==id) {
					restaurant.setVisibility(false);
				}
			}
		}
	}
	public void deleteArticle(int id) {
		if(articleExist(id)) {
			for(Article article : articles.values()) {
				if(article.getId()==id) {
					article.setVisibility(false);
				}
			}
		}
	}
	public void deleteVehicle(String register) {
		if(vehicleExist(register)) {
			for(Vehicle vehicle : vehicles.values()) {
				if(vehicle.getRegister().equalsIgnoreCase(register)) {
					vehicle.setVisibility(false);
				}
			}
		}
	}


	public void deleteOrder(int id) {
		if(orderExist(id)) {
			for(Order order: orders.values()) {
				if(order.getId()==id) {
					order.setVisibility(false);
				}
			}
		}
		
	}


	

}

