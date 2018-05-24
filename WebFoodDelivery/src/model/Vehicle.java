package model;

import java.util.Date;

public class Vehicle {

	private int id;
	private String brand;
	private String model;
	private Type tip;
	private String register;
	private Date yearOfProduction;
	private boolean inUse;
	private String note;
	
	public Vehicle() {
		super();
	}

	public Vehicle(int id,String brand, String model, Type tip, String register, Date yearOfProduction, boolean inUse,
			String note) {
		super();
		this.id=id;
		this.brand = brand;
		this.model = model;
		this.tip = tip;
		this.register = register;
		this.yearOfProduction = yearOfProduction;
		this.inUse = inUse;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Type getTip() {
		return tip;
	}

	public void setTip(Type tip) {
		this.tip = tip;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}

	public Date getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(Date yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	public boolean isInUse() {
		return inUse;
	}

	public void setInUse(boolean inUse) {
		this.inUse = inUse;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	

}
