package services;


import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import model.Database;


public class DatabaseService {

	@Context
	public ServletContext context;
	
	public Database getDataBase() {
		Database database = (Database) context.getAttribute("database");
		if (database == null) {
			database = new Database(context.getRealPath(""));
			context.setAttribute("database", database);
		} 
		return database;
	}
	

}
