package services;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import model.Role;
import model.User;

@Path("/users")
public class UserService extends DatabaseService {
	
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
	public Response signIn(User user/*,@Context HttpServletRequest request*/)  {
		if(getDataBase().loginCheck(user)) {
			
			User userToReturn = new User();
			for(User user1 : getDataBase().getUsers().values()) {
				if(user1.getUsername().equals(user.getUsername())) {
					userToReturn = user1;
				}
			}
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json="";
			try {
				json = ow.writeValueAsString(userToReturn);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}

//			  User retVal = null;
//			  retVal =  (User)request.getSession().
//					getAttribute("user");
//			  if (retVal == null) {
//				  request.getSession().setAttribute("user", user);
//				  retVal = user;
//			  }
//			  return retVal;
			return Response.ok(json,MediaType.APPLICATION_JSON).build();
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

		user.setRole(Role.valueOf("CUSTOMER"));
		int futureUserId = getDataBase().getUsers().size() + 1;
		user.setId(futureUserId);
		getDataBase().getUsers().put(user.getId(),user);
		getDataBase().writeData();
		User user1= user;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json="";
		try {
			json = ow.writeValueAsString(user1);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return Response.ok(json,MediaType.APPLICATION_JSON).build();
	}
	
	@PUT
	@Path("/updateUser") // role
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
 	public Response updateUser(User user) {
			user.setRole(user.getRole());
			getDataBase().getUsers().put(user.getId(),user);
			getDataBase().writeData();
			return Response.ok().build();
	}

}
