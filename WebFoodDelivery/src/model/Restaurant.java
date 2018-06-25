package model;


public class Restaurant {

	private int id;
	private String name;
	private String adress;
	private Category category;

	public Restaurant(int id, String name, String adress, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
		this.category = category;
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

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

}
