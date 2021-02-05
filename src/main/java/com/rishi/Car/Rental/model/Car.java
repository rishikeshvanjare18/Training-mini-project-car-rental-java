package com.rishi.Car.Rental.model;

public class Car {
	int carid;
	String cname;
	int cprice;
	String cdescription;
	int seater;
	String cimage;
	public Car(int carid, String cname, int cprice, String cdescription, int seater, String cimage) {
		super();
		this.carid = carid;
		this.cname = cname;
		this.cprice = cprice;
		this.cdescription = cdescription;
		this.seater = seater;
		this.cimage = cimage;
	}
	public Car() {
		super();
	}
	public int getCarid() {
		return carid;
	}
	public void setCarid(int carid) {
		this.carid = carid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getCprice() {
		return cprice;
	}
	public void setCprice(int cprice) {
		this.cprice = cprice;
	}
	public String getCdescription() {
		return cdescription;
	}
	public void setCdescription(String cdescription) {
		this.cdescription = cdescription;
	}
	public int getSeater() {
		return seater;
	}
	public void setSeater(int seater) {
		this.seater = seater;
	}
	public String getCimage() {
		return cimage;
	}
	public void setCimage(String cimage) {
		this.cimage = cimage;
	}
	
	
	
}
