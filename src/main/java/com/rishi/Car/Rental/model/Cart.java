package com.rishi.Car.Rental.model;

public class Cart {
	int Cartid;
	int Carid;
	int Custid;
	public Cart(int cartid, int carid, int custid) {
		super();
		Cartid = cartid;
		Carid = carid;
		Custid = custid;
	}
	
	
	public Cart() {
		super();
	}



	public int getCartid() {
		return Cartid;
	}
	public void setCartid(int cartid) {
		Cartid = cartid;
	}
	public int getCarid() {
		return Carid;
	}
	public void setCarid(int carid) {
		Carid = carid;
	}
	public int getCustid() {
		return Custid;
	}
	public void setCustid(int custid) {
		Custid = custid;
	}
	
	
	
	
}
