package model;

import java.io.Serializable;
import java.util.List;

public class Customer extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Order> orders;
	private List<Restaurant> favoriteRestaurants;
	
	public Customer(String path) {
		// TODO Auto-generated constructor stub
	}
	

	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Restaurant> getFavoriteRestaurants() {
		return favoriteRestaurants;
	}
	public void setFavoriteRestaurants(List<Restaurant> favoriteRestaurants) {
		this.favoriteRestaurants = favoriteRestaurants;
	}
	
	
}
