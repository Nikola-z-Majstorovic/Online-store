package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.Article;
import model.Database;
import model.RestArtic;
import model.Restaurant;
import model.User;
import model.Vehicle;


@Path("/database")
public class DatabaseService {

	@Context
	ServletContext context;

	
	@GET
	@Path("/getUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<User> getUsers() {
		return getDataBase().getUsers().values();
	}
	@GET
	@Path("/getRestaurants")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Restaurant> getRestaurants() {
		return getDataBase().getRestaurants().values();

	}
	@GET
	@Path("/getRestArtic")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<RestArtic> getRestArtic() {
		return getDataBase().getResArtics();
	}
	@GET
	@Path("/getArticles")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Article> getArticles() {
		return getDataBase().getArticles().values();
	}
	@POST
	@Path("/signIn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signIn(User user) throws JsonProcessingException {
		if(getDataBase().loginCheck(user)) {
			User obj=getDataBase().getUsers().get(user.getUsername());
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(obj);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		}
		else {
			return Response.status(Status.NOT_FOUND).entity("{\"msg\":\"User not found\"}").build();
		}
	}
	
	@POST
	@Path("/signUp")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		if(getDataBase().usernameExists(user.getUsername())) { 
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Duplicate username\"}").build();
		}
		else if(getDataBase().emailExists(user.getEmail())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Duplicate email\"}").build();
		}
		
		getDataBase().getUsers().put(user.getUsername(),user);
		getDataBase().writeData();
		return Response.ok().build();
	}

	@POST
	@Path("/addRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRestaurant(Restaurant restaurant) {
		if(getDataBase().restaurantExist(restaurant.getId())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Restaurant already exists\"}").build();
		}
		getDataBase().getRestaurants().put(restaurant.getId(), restaurant);
		getDataBase().writeData();
		return Response.ok().build();
	
	}
	@POST
	@Path("/addVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addVehicle(Vehicle vehicle) {
		if(getDataBase().vehicleExist(vehicle.getRegister())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Vehicle already exists\"}").build();
		}
		getDataBase().getVehicles().put(vehicle.getRegister(),vehicle);
		getDataBase().writeData();
		return Response.ok().build();
	}
		
	@POST
	@Path("/modifyUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
 	public Response modifyUser(User user) {
		if(getDataBase().usernameExists(user.getName())){
			getDataBase().getUsers().put(user.getUsername(),user);
			getDataBase().writeData();
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).entity("{\"msg\":\"User not found\"}").build();
	}
	@POST
	@Path("/modifyRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response modifyRestaurant(Restaurant restaurant) {
		if(getDataBase().restaurantExist(restaurant.getId())) {
			getDataBase().getRestaurants().put(restaurant.getId(), restaurant);
			getDataBase().writeData();
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).entity("{\"msg\":\"User not found\"}").build();
		
	}
	@POST
	@Path("/modifyVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response modifyVehicle(Vehicle vehicle)	{
		if(getDataBase().vehicleExist(vehicle.getRegister())) {
			getDataBase().getVehicles().put(vehicle.getRegister(),vehicle);
			getDataBase().writeData();
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).entity("{\"msg\":\"Vehicle not found\"}").build();		
		
	}
	@DELETE
	@Path("/deleteRestaurant/{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteRestaurant(@PathParam("name")int id ) {
		if(getDataBase().restaurantExist(id)) {
			getDataBase().getRestaurants().remove(id);
			getDataBase().writeData();
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).entity("{\"msg\":\"Error updating Restaurant\"}").build();
	}
	


	@DELETE
	@Path("/deleteVehicle/{register}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteFood(@PathParam("register") String register) {
		if(getDataBase().vehicleExist(register)) {
			getDataBase().getVehicles().remove(register);
			getDataBase().writeData();
			return Response.ok().build();
		}
		return Response.status(Status.NOT_FOUND).entity("{\"msg\":\"Error updating Vehicle\"}").build();
	}
	@GET
	@Path("/readArticlesFromRestaurant/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Article> readArticlesFromRestaurant(@PathParam("id") int id) {
		return getDataBase().readArticlesFromRestaurant(id);
	}
	
	
	private Database getDataBase() {
		Database database = (Database) context.getAttribute("database");
		if (database == null) {
			database = new Database(context.getRealPath(""));
			context.setAttribute("database", database);
		} 
		return database;
	}
	

}
