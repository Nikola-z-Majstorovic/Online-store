package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Restaurant;

@Path("/restaurants")
public class RestaurantService extends DatabaseService {
	
	@GET
	@Path("/getRestaurants")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Restaurant> getRestaurants() {
		return getDataBase().getRestaurants().values();
	}
	
	@GET
	@Path("/getRestaurantFromArticle/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Restaurant> getRestaurantFromArticle(@PathParam("id") int id) {
		return getDataBase().readRestaurantFromArticle(id);
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
	@PUT
	@Path("/updateRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public Response updateRestaurant(Restaurant restaurant) {
			getDataBase().getRestaurants().put(restaurant.getId(),restaurant);
			getDataBase().writeData();
			return Response.ok().build();
	}
	
	@DELETE
	@Path("/deleteRestaurant/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRestaurant(@PathParam("id")int id ) {
		getDataBase().deleteRestaurant(id);
		getDataBase().writeData();
		return Response.ok().build();
	}
}
