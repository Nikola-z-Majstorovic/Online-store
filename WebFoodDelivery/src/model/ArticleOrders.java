package model;

public class ArticleOrders {

	private int idA;
	private int amount;
	
	public ArticleOrders() {
		
	}
	
	public ArticleOrders(int idA, int amount) {
		super();
		this.idA = idA;
		this.amount = amount;
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
	
}
