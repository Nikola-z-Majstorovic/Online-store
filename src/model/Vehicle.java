package model;


public class Vehicle {

	private String brand; //fiat
	private String model;//punto
	private Type type;
	private String register;
	private String yearOfProduction;
	private boolean inUse;
	private String note;
	private boolean visibility;
	
	public Vehicle() {
		super();
	}
	
	public Vehicle(String brand, String model, Type type, String register,String yearOfProduction, boolean inUse,
			String note, boolean visibility) {
		super();
		this.brand = brand;
		this.model = model;
		this.type = type;
		this.register = register;
		this.setYearOfProduction(yearOfProduction);
		this.inUse = inUse;
		this.note = note;
		this.visibility = visibility;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type= type;
	}

	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
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

	public boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}


	public String getYearOfProduction() {
		return yearOfProduction;
	}

	public void setYearOfProduction(String yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}

	@Override
	public String toString() {
		return "Vehicle [brand=" + brand + ", model=" + model + ", type=" + type + ", register=" + register
				+ ", yearOfProduction=" + yearOfProduction + ", inUse=" + inUse + ", note=" + note + ", visibility="
				+ visibility + "]";
	}	

}
