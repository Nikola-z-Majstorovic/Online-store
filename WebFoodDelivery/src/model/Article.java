package model;

public class Article {

	private int id;
	private String name;
	private int price;
	private String description;
	private float amount;
	
	public Article() {
		super();
	}

	public Article(int id, String name, int price, String description, float amount) {
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	
}
