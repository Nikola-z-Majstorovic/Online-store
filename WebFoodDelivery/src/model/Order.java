package model;

import java.util.ArrayList;


public class Order {

	private int id;
	private String dateOfOrder;
	private int idC;
	private String	idD;
	private int idR;
	private Status status;
	private String note;
	private boolean visibility;
	private ArrayList<ArticleOrders> articleOrders;
	private double price;
	
	public Order() {
		
	}
	
	public Order(int id,String dateOfOrder,int idC, String idD, int idR, Status status, String note, ArrayList<ArticleOrders> articleOrders,boolean visibility,double price
			) {
		super();
		this.id = id;
		this.dateOfOrder = dateOfOrder;
		this.idC = idC;
		this.idD = idD;
		this.idR = idR;
		this.status = status;
		this.note = note;
		this.visibility = visibility;
		this.articleOrders = articleOrders;
		this.price = price;
	}
 
	public String getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(String dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getIdC() {
		return idC;
	}
	public void setIdC(int idC) {
		this.idC = idC;
	}
	public String getIdD() {
		return idD;
	}
	public void setIdD(String idD) {
		this.idD = idD;
	}
	public int getIdR() {
		return idR;
	}
	public void setIdR(int idR) {
		this.idR = idR;
	}

	public boolean isVisibility() {
		return visibility;
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public ArrayList<ArticleOrders> getArticleOrders() {
		return articleOrders;
	}

	public void setArticleOrders(ArrayList<ArticleOrders> articleOrders) {
		this.articleOrders = articleOrders;
	}



	@Override
	public String toString() {
		return "Order [id=" + id + ", dateOfOrder=" + dateOfOrder + ", idC=" + idC + ", idD=" + idD + ", idR=" + idR
				+ ", status=" + status + ", note=" + note + ", visibility=" + visibility + ", articleOrders="
				+ articleOrders + ", price=" + price + "]";
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
