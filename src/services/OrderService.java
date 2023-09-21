package services;

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

import model.Order;

@Path("/orders")
public class OrderService extends DatabaseService {
	
	@GET
	@Path("/getOrders")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Order> getArticles() {
		return getDataBase().getOrders().values();
	}

	@POST
	@Path("/addOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOrder(Order order) {
		if(getDataBase().orderExist(order.getId())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Order already exists\"}").build();
		}
		getDataBase().getOrders().put(order.getId(), order);
		getDataBase().writeData();
		return Response.ok().build();
	}
	
	@PUT
	@Path("/updateOrder")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOrder(Order order) {
		getDataBase().getOrders().put(order.getId(),order);
		getDataBase().writeData();
		return Response.ok().build();

	}
	@DELETE
	@Path("/deleteOrder/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteOrder(@PathParam("id")int id) {
		getDataBase().deleteOrder(id);
		getDataBase().writeData();
		return Response.ok().build();
	}
}
