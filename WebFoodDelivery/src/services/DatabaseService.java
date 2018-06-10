package services;

import java.util.Collection;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Database;
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
	
	@POST
	@Path("/signIn")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signIn(User user) {
		if(getDataBase().loginCheck(user)) {
			return Response.ok().build();
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
		if(getDataBase().restaurantExist(restaurant.getName())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Restaurant already exists\"}").build();
		}
		getDataBase().getRestaurants().put(restaurant.getName(), restaurant);
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
	
	
	
	private Database getDataBase() {
		Database database = (Database) context.getAttribute("database");
		if (database == null) {
			database = new Database(context.getRealPath(""));
			context.setAttribute("database", database);
		} 
		return database;
	}

}
