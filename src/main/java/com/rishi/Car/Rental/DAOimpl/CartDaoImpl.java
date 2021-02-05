package com.rishi.Car.Rental.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rishi.Car.Rental.DAO.CartDAO;
import com.rishi.Car.Rental.model.Cart;
import com.rishi.Car.Rental.util.DButil;

@Repository
public class CartDaoImpl implements CartDAO{
	static List<Cart> list1=new ArrayList();

	Connection connection;
	
	public CartDaoImpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}	
	
	public List<Cart> viewAllCarts(){
		//List<Car> car = new ArrayList<Car>();		
		//System.out.println("Inside viewAll carts");
		try {
			
			//System.out.println("Inside try");
			PreparedStatement stmt = connection.prepareStatement("select * from cart;");
			ResultSet rs = stmt.executeQuery();
			list1.clear();
			while (rs.next()) {
				Cart c=new Cart();
				
				//System.out.println("Inside while");
				c.setCartid(rs.getInt(1));
				c.setCustid(rs.getInt(2));
				c.setCarid(rs.getInt(3));
			
				
			//	System.out.println("Inside while-2");
				list1.add(c);
				//System.out.println(list1);				
			}
			}catch(Exception e) {System.out.println(e);}
		return list1;
	}
	

	
	public void addCart(Cart cart) {
		System.out.println("-----------------Inside add cart\n");
		String query = "insert into cart values(" + cart.getCartid()+ ","
											+cart.getCustid()+","
											+cart.getCarid()+")";
		System.out.println(query);
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Cart INSERTED SUCCESSFULY!!\n\n"); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int deleteCart(int id) {
		int status = 0;
		String query = "delete from cart where cartid ="+id;
		//System.out.println(query);
		try {
			Statement st = connection.createStatement();
			status = st.executeUpdate(query);
		//	System.out.println(" Cart Deleted SUCCESSFULY!!\n\n"); 
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	

	public Cart viewoneCart(int cartid) {
		// TODO Auto-generated method stub
//	System.out.println(list1);
//	System.out.println(cartid);
//	System.out.println(list1.get(0).getCartid());
	
		for (Cart c : list1) {
			if (c.getCarid() == cartid) {
			//	System.out.println("cartid is equal");
			//	System.out.println("------------"+list1);	
				return c;
			}
		}
	
		return null;

	}


}
