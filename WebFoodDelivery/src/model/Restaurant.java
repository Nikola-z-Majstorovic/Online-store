package model;

import java.util.List;

public class Restaurant {

	private String name;
	private String adress;
	private Category category;
	private List<Article> foods;
	private List<Article> drinks;
	
	public Restaurant() {
		super();
	}

	public Restaurant(String name, String adress, Category category, List<Article> foods, List<Article> drinks) {
		super();
		this.name = name;
		this.adress = adress;
		this.category = category;
		this.foods = foods;
		this.drinks = drinks;
	}

	public String getName() {
		return name;
	}

	public void setName(String id) {
		this.name = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Article> getFoods() {
		return foods;
	}

	public void setFoods(List<Article> foods) {
		this.foods = foods;
	}

	public List<Article> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<Article> drinks) {
		this.drinks = drinks;
	}
	


}
