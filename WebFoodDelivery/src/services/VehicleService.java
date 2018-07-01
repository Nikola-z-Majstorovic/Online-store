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

import model.Vehicle;


@Path("/vehicles")
public class VehicleService extends DatabaseService{
	
	@GET
	@Path("/getVehicles")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Vehicle> getVehicles() {
		return getDataBase().getVehicles().values();
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

	@PUT
	@Path("/updateVehicle")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON) 
	public Response updateVehicle(Vehicle vehicle)	{
		getDataBase().getVehicles().put(vehicle.getRegister(),vehicle);
		getDataBase().writeData();
		return Response.ok().build();
		
	}
	
	
	
	@DELETE
	@Path("/deleteVehicle/{register}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVehicle(@PathParam("register") String register) {
			getDataBase().deleteVehicle(register);
			getDataBase().writeData();
			return Response.ok().build();
	}
	
}
