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

import model.Article;

@Path("/articles")
public class ArticleService extends DatabaseService {
	
	@GET
	@Path("/getArticles")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Article> getArticles() {
		return getDataBase().getArticles().values();
	}
	@GET
	@Path("/getArticlesFromRestaurant/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Article> getArticlesFromRestaurant(@PathParam("id") int id) {
		return getDataBase().readArticlesFromRestaurant(id);
	}
	@POST
	@Path("/addArticleToRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addArticleToRestaurant(Article article){
		if(getDataBase().articleExist(article.getId())) {
			return Response.status(Status.CONFLICT).entity("{\"msg\":\"Article already exists\"}").build();
		}
			getDataBase().getArticles().put(article.getId(), article);
			getDataBase().writeData();
		return Response.ok().build();
	
	}
	@PUT
	@Path("/updateArticlesFromRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateArticlesFromRestaurant(Article article) {
		getDataBase().getArticles().put(article.getId(),article);
		getDataBase().writeData();
		return Response.ok().build();
		
	}
	@DELETE
	@Path("/deleteArticlesFromRestaurant/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteArticlesFromRestaurant(@PathParam("id") int id) {
		getDataBase().deleteArticle(id);
		getDataBase().writeData();
		return Response.ok().build();
	}
	
}
