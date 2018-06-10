package model;

import java.util.ArrayList;


public class Restaurant {

	private String name;
	private String adress;
	private Category category;
	private ArrayList<Article> foods;
	private ArrayList<Article> drinks;
	
	public Restaurant() {
		super();
	}

	public Restaurant(String name, String adress, Category category, ArrayList<Article> foods,ArrayList<Article> drinks) {
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

	public ArrayList<Article> getFoods() {
		return foods;
	}

	public void setFoods(ArrayList<Article> foods) {
		this.foods = foods;
	}

	public ArrayList<Article> getDrinks() {
		return drinks;
	}

	public void setDrinks(ArrayList<Article> drinks) {
		this.drinks = drinks;
	}

}
