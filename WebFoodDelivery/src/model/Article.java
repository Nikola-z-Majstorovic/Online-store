package model;

public class Article {

	private int id;
	private String name;
	private double price;
	private String description;
	private double amount;
	
	public Article() {
		super();
	}

	public Article(int id, String name, double price, String description, double amount) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.amount = amount;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public double getPrice() {
		return price;
	}



	public void setPrice(double price) {
		this.price = price;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	
}
