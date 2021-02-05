package com.rishi.Car.Rental.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.Car.Rental.DAO.CustomerDAO;
import com.rishi.Car.Rental.model.Customer;
import com.rishi.Car.Rental.util.DButil;




@Repository
public class CustomerDaoImpl implements CustomerDAO{
	static List<Customer> list1=new ArrayList();

	Connection connection;
	
	public CustomerDaoImpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}	
	
	public List<Customer> viewAllCustomer(){
		//List<Customer> customer = new ArrayList<Customer>();		
		System.out.println("Inside viewAll customer");
		try {
			
			System.out.println("Inside try");
			PreparedStatement stmt = connection.prepareStatement("select * from customer;");
			ResultSet rs = stmt.executeQuery();
			list1.clear();
			while (rs.next()) {
				Customer cust=new Customer();
				
				System.out.println("Inside while");
				cust.setCustid(rs.getInt(1));
				cust.setUsername(rs.getString(2));
				cust.setPassword(rs.getString(3));
				cust.setEmail(rs.getString(4));
				cust.setMobno(rs.getLong(5));
				cust.setAddress(rs.getString(6));
				cust.setName(rs.getString(7));
				
				System.out.println("Inside while-2");
				list1.add(cust);
				System.out.println(list1);				
			}
			}catch(Exception e) {System.out.println(e);}
		return list1;
	}
	

	
	public void addCustomer(Customer customer) {
		String query = "insert into customer values(" + customer.getCustid()
				+ ",'"+customer.getUsername()+"','"+customer.getPassword()+"','"+customer.getEmail()+"',"+customer.getMobno()+",'"+customer.getAddress()+"','"+customer.getName()+"')";
		System.out.println(query);
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
			System.out.println("INSERTED SUCCESSFULY!!\n\n"); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int deleteCustomer(int id) {
		int status = 0;
		String query = "delete from customer where custid ="+id;
		System.out.println(query);
		try {
			Statement st = connection.createStatement();
			status = st.executeUpdate(query);
			System.out.println("Deleted SUCCESSFULY!!\n\n"); 
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	
	
	@Override
	public int loginValidation(String username,String password){
		viewAllCustomer();
		int flag=0;
		System.out.println(list1);
		for (Customer c:list1) {
			System.out.println("Customer data----"+c);
			if (c.getUsername().equals(username)  && c.getPassword().equals(password)) {
				flag=1;							
				//return flag;
			}
		}
		System.out.println("flag"+flag);
		return flag;
	}

	public Customer viewoneCustomer(int custid) {
		// TODO Auto-generated method stub
	System.out.println(list1);
	System.out.println(custid);
	System.out.println(list1.get(0).getUsername());
	
		for (Customer c : list1) {
			if (c.getCustid() == custid) {
				System.out.println("custid is equal");
				System.out.println("------------"+list1);	
				return c;
			}
		}
	
		return null;

	}

	@Override
	public void updateCustomer(int custid,Customer customer) {
		System.out.println("in dao Impl");
		int noOfRecords = 0;
		try {
			PreparedStatement pst = connection.prepareStatement(
					"update customer set username=?,password=?,email=?,mobno=?,address=?,name=? where custid=? ");
			
			pst.setString(1, customer.getUsername());
			pst.setString(2, customer.getPassword());
			pst.setString(3, customer.getEmail());
			pst.setLong(4, customer.getMobno());
			pst.setString(5, customer.getAddress());
			pst.setString(6, customer.getName());
			pst.setInt(7, customer.getCustid());

			noOfRecords = pst.executeUpdate();
			System.out.println(noOfRecords + "record updated successfully");
		} catch (Exception e) {
		}
		
	}

	@Override
	public int viewonecustomerbyUsername(String username) {
		//System.out.println(list1);
		//System.out.println(username);
//		System.out.println("In Impl List..."+list1.get(0).getUsername());
		
			for (Customer c : list1) {
				if (c.getUsername().equals(username)) {
					System.out.println("in comaparison"+c.getUsername());
					System.out.println("Actual "+username);	
					return c.custid;
				}
			}
		
			return 0;
	}

//	@Override
//	public void updateCustomer(Customer customer,int custid) {
//		
//		for(Customer c:list1) {
//			
//		String query = "update customer set name='"+customer.getName()+"',"+
//											"username = '"+customer.getUsername()+"',"+
//											"password = '"+customer.getPassword()+"',"+
//											"email = '"+customer.getEmail()+ "',"+
//											"mobno = "+customer.getMobno()+ ","+
//											"address = '"+customer.getAddress()+ "',"+
//											"where custid = "+customer.getCustid();
//		System.out.println(query);
//		
//		
//		
//		try {
//			Statement st = connection.createStatement();
//			st.executeUpdate(query);
//			System.out.println("Updated SUCCESSFULY!!\n\n"); 
//			int index = list1.lastIndexOf(c);
//			list1.set(index, customer);
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//		
//		}
//		System.out.println(list1);
//	}



//	@Override
//	public void updateCustomer(Customer customer, int custid) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void updateProfile(int id, Customer customer) {
//		
//		for (Customer c : list1) {
//			if (c.getCustid() == id) {
//				
//				
//				
//				System.out.println("update inside");
//				try {
////					Connection conn = Connection.getConnection();
//					Statement stmt = connection.createStatement();
//					stmt.executeUpdate("update customer set name='"+customer.getName()+"',"+
//							"username = '"+customer.getUsername()+"',"+
//							"password = '"+customer.getPassword()+"',"+
//							"email = '"+customer.getEmail()+ "',"+
//							"mobno = "+customer.getMobno()+ ","+
//							"address = '"+customer.getAddress()+ "',"+
//							"where custid = "+customer.getCustid(););
//					System.out.println("successfull updated......!");
//					
//					int index=list1.indexOf(c);
//					
//					list1.set(index, customer);
//					
//					
//				} catch (Exception e) {
//					System.out.println(e);
//				}
//
//			}
//		}
//		
//		System.out.println(list1);
//	}
//
//	


}
