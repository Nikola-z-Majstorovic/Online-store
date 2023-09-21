package model;

public class ArticleOrders {

	private int idA;
	private int amount;
	private String name;
	private double priceByUnit;
	
	public ArticleOrders() {
		
	}
	
	public ArticleOrders(int idA, int amount,String name,double priceByUnit) {
		super();
		this.idA = idA;
		this.amount = amount;
		this.name = name;
		this.setPriceByUnit(priceByUnit);
	}

	public int getIdA() {
		return idA;
	}

	public void setIdA(int idA) {
		this.idA = idA;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPriceByUnit() {
		return priceByUnit;
	}

	public void setPriceByUnit(double priceByUnit) {
		this.priceByUnit = priceByUnit;
	}

	@Override
	public String toString() {
		return "ArticleOrders [idA=" + idA + ", amount=" + amount + ", name=" + name + ", priceByUnit=" + priceByUnit
				+ "]";
	}
	
}
