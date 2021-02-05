package com.rishi.Car.Rental.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rishi.Car.Rental.model.Cart;

@Repository
public interface CartDAO {
	public List<Cart> viewAllCarts();
	public void addCart(Cart cart);
	public int deleteCart(int cartid);
	public  Cart viewoneCart(int cartid);
}
