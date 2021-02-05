package com.rishi.Car.Rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import com.example.rishi.controller.Valid;
//import com.example.rishi.exception.ResourceNotFoundException;
//import com.example.rishi.model.Account;
import com.rishi.Car.Rental.DAO.CustomerDAO;
import com.rishi.Car.Rental.model.Customer;


@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class CustomerController {
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@GetMapping("/Customer")
	public List getCustomers() {	
	return customerDAO.viewAllCustomer();
	}
	
	@GetMapping("/Customer/{custid}")
	public ResponseEntity getonecustomer(@PathVariable("custid") int custid) {
		Customer customer=customerDAO.viewoneCustomer(custid);
		
		if (customer == null) {
			return new ResponseEntity("No Customer found for USERNAME " + custid, HttpStatus.NOT_FOUND);
		}
		System.out.println("new name="+customer.getName());
		return new ResponseEntity(customer, HttpStatus.OK);
	}
	
	@GetMapping("/Customer/get/{username}")
	public int getonecustomer(@PathVariable("username") String username) {
		int custid=customerDAO.viewonecustomerbyUsername(username);
		
		if (custid != 0) {
			return custid;
		}
		//System.out.println("in the viewonecustomerbyUsername Controller="+customer);
		return 0;
	}
	
	@GetMapping("/Customer/{username}/{password}")
	public ResponseEntity getCustomerLogin(@PathVariable("username") String username,@PathVariable("password") String password) {		
		int flag=customerDAO.loginValidation(username,password);			
		if (flag == 0) {
			return new ResponseEntity("No Customer found for ID " + username, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(flag, HttpStatus.OK);
	}
	
	@PostMapping(value = "/post/Customer")
	public ResponseEntity addCustomer(@RequestBody Customer customer) {
		customerDAO.addCustomer(customer);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/deleteCustomer/{custid}")
	public ResponseEntity deleteCustomer(@PathVariable("custid") int id) {		
		int flag=customerDAO.deleteCustomer(id);			
		
		if(flag==0) {
			return new ResponseEntity("Error delete",HttpStatus.OK);
		}
		return new ResponseEntity("Cid:"+ id +"customer Record deleted successfully",HttpStatus.OK);
	}
	
//	@PutMapping("/updateCustomer/{custid}")
//	public ResponseEntity UpdateCustomer(@RequestBody Customer customer) {
//		
//		customerDAO.updateCustomer(customer,customer.custid);
//		
//		return new ResponseEntity(customer,HttpStatus.OK);
//	}
	
//	@PutMapping(value = "/put/Customer/{id}")
//	public ResponseEntity createCustomer(@PathVariable("id") int id,@RequestBody Customer cust ) {
//
//		customerDAO.updateprofile(id, cust);
//		return new ResponseEntity(cust, HttpStatus.OK);
//	}
//	
	
	@PutMapping(value = "/update/Customer/{id}") 
	public ResponseEntity updateCustomer(@PathVariable("id") int id,@RequestBody Customer customer)
	{
		//System.out.println("in controller");
		customerDAO.updateCustomer(customer.getCustid(),customer);
		return new ResponseEntity(customer,HttpStatus.OK);
	}
	
	

}