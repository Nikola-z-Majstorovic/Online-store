package model;

public class Article {

	private int id;
	private String name;
	private double price;
	private String description;
	private String artImgPath;
	private String quantity;
	private boolean isFood;
	private boolean visibility;
	private int idR;
		
	public Article() {
		
	}
	
	public Article(int id, String name, double price, String description, String artImgPath, String quantity,
			boolean isFood, boolean visibility, int idR) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.artImgPath = artImgPath;
		this.quantity = quantity;
		this.isFood = isFood;
		this.visibility = visibility;
		this.idR = idR;
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

	public boolean getVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public String getArtImgPath() {
		return artImgPath;
	}


	public void setArtImgPath(String artImgPath) {
		this.artImgPath = artImgPath;
	}

	public int getIdR() {
		return idR;
	}

	public void setIdR(int idR) {
		this.idR = idR;
	}

	public boolean isFood() {
		return isFood;
	}

	public void setFood(boolean isFood) {
		this.isFood = isFood;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", artImgPath=" + artImgPath + ", quantity=" + quantity + ", isFood=" + isFood + ", visibility="
				+ visibility + ", idR=" + idR + "]";
	}

	
}
