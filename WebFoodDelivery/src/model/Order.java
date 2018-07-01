package model;

import java.util.ArrayList;


public class Order {

	private int id;
//	private Date dateOfOrder;
	private int idC;
	private int	idD;
	private int idR;
	private Status status;
	private String note;
	private boolean visibility;
	private ArrayList<ArticleOrders> articleOrders;
	
	public Order() {
		
	}
	
	public Order(int id,int idC, int idD, int idR, Status status, String note, ArrayList<ArticleOrders> articleOrders,boolean visibility
			) {
		super();
		this.id = id;
//		this.dateOfOrder = dateOfOrder;
		this.idC = idC;
		this.idD = idD;
		this.idR = idR;
		this.status = status;
		this.note = note;
		this.visibility = visibility;
		this.articleOrders = articleOrders;
	}
//
//	public Date getDateOfOrder() {
//		return dateOfOrder;
//	}
//	public void setDateOfOrder(Date dateOfOrder) {
//		this.dateOfOrder = dateOfOrder;
//	}

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
	public int getIdD() {
		return idD;
	}
	public void setIdD(int idD) {
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
		return "Order [id=" + id + ", idC=" + idC + ", idD=" + idD + ", idR=" + idR
				+ ", status=" + status + ", note=" + note + ", visibility=" + visibility + ", articleOrders="
				+ articleOrders + "]";
	}

}
