package service;

import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import model.Customer;
import model.Database;


@Path("/database")
public class DatabaseService {

	@Context
	HttpServletRequest request;
	@Context
	ServletContext context;

	
	@POST
	@Path("/addCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer) {
		if(getDataBase().usernameExists(customer.getName())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Duplicate username\"}").build();
		}
		else if(getDataBase().emailExists(customer.getEmail())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Duplicate email\"}").build();
		}
		getDataBase().getCustomers().put(customer.getName(), customer);
		getDataBase().writeData();
		return Response.ok().build();
	}

	@GET
	@Path("/getCustomers")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Customer> getCustomers() {
		return getDataBase().getCustomerValues();
	}
	
	private Database getDataBase() {
		Database database = (Database) context.getAttribute("database");
		if (database == null) {
			database = new Database(context.getRealPath(""));
			context.setAttribute("customer", database);
		} 
		return database;
	}
}
