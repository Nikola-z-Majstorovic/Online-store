package model;

import java.util.List;

public class Customer extends User {

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
