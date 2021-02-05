package com.rishi.Car.Rental.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rishi.Car.Rental.model.Customer;

@Repository
public interface CustomerDAO {
	public List<Customer> viewAllCustomer();
	public void updateCustomer(int custid,Customer customer);
	public void addCustomer(Customer customer);
	public int loginValidation(String username,String password);
	public int deleteCustomer(int id);
	public  Customer viewoneCustomer(int custid);
	public int viewonecustomerbyUsername(String username);
}
