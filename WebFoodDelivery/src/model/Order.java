package model;

import java.util.Date;


public class Order {

	private int id;
	private Date dateOfOrder;
	private Customer customer;
	private Deliverer deliverer;
	private Status status;
	private boolean delivered;
	private String note;
	
	public Order(int id, Date dateOfOrder, Customer customer, Deliverer deliverer, Status status, boolean delivered,
			String note) {
		super();
		this.setId(id);
		this.dateOfOrder = dateOfOrder;
		this.customer = customer;
		this.deliverer = deliverer;
		this.status = status;
		this.delivered = delivered;
		this.note = note;
	}
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
