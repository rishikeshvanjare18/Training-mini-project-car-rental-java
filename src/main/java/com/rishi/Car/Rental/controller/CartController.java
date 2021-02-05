package com.rishi.Car.Rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.Car.Rental.DAO.CartDAO;
import com.rishi.Car.Rental.model.Cart;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class CartController {
	@Autowired
	private CartDAO cartDao;
	
	@GetMapping("/Cart")
	public List getCars() {	
	return cartDao.viewAllCarts();
	}
	
	@GetMapping("/Cart/{cartid}")
	public ResponseEntity getonecart(@PathVariable("cartid") int cartid) {
		Cart cart=cartDao.viewoneCart(cartid);
		
		if (cart == null) {
			return new ResponseEntity("No Cart found for carid " + cartid, HttpStatus.NOT_FOUND);
		}
		System.out.println("new name="+cart.getCarid());
		return new ResponseEntity(cart, HttpStatus.OK);
	}
	
	
	@GetMapping("/post/Cart/{custid}/{carid}")
	public int CreateCart(@PathVariable("custid") int custid,@PathVariable("carid") int carid) {
		Cart c = new Cart();
		System.out.println("----------------------inside cart controller-------------------------");
		c.setCustid(custid);
		c.setCarid(carid);
		
		System.out.println(c.getCustid()+" "+c.getCarid());
		
		cartDao.addCart(c);
		//return new ResponseEntity(c,HttpStatus.OK);
		
		return 1;
		
	}
	
	@GetMapping("/deleteCart/{cartid}")
	public ResponseEntity deleteCart(@PathVariable("cartid") int id) {		
		int flag=cartDao.deleteCart(id);			
		
		if(flag==0) {
			return new ResponseEntity("Error delete",HttpStatus.OK);
		}
		return new ResponseEntity("Cartid:"+ id +"cart Record deleted successfully",HttpStatus.OK);
	}
		
	
}
