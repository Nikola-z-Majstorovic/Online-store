package model;

import java.util.Date;
import java.util.List;


public class Order {

	
	private Date dateOfOrder;
	private Customer customer;
	private Deliverer deliverer;
	private Status status;
	private boolean delivered;
	private String note;
	private List<String> comments;
	
	public Date getDateOfOrder() {
		return dateOfOrder;
	}
	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Deliverer getDeliverer() {
		return deliverer;
	}
	public void setDeliverer(Deliverer deliverer) {
		this.deliverer = deliverer;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public boolean isDelivered() {
		return delivered;
	}
	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}

	
}
