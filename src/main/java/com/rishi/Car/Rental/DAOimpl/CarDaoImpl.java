package com.rishi.Car.Rental.DAOimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rishi.Car.Rental.DAO.CarDAO;
import com.rishi.Car.Rental.DAO.CustomerDAO;
import com.rishi.Car.Rental.model.Car;
import com.rishi.Car.Rental.util.DButil;

@Repository
public class CarDaoImpl  implements CarDAO{
		
	static List<Car> list1=new ArrayList();

	Connection connection;
	
	public CarDaoImpl() {
		connection = DButil.getConnection();
		System.out.println("connection" + connection);
	}	
	
	public List<Car> viewAllCars(){
		//List<Car> car = new ArrayList<Car>();		
		System.out.println("Inside viewAll cars");
		try {
			
			System.out.println("Inside try");
			PreparedStatement stmt = connection.prepareStatement("select * from cars;");
			ResultSet rs = stmt.executeQuery();
			list1.clear();
			while (rs.next()) {
				Car c=new Car();
				
				System.out.println("Inside while");
				c.setCarid(rs.getInt(1));
				c.setCname(rs.getString(2));
				c.setCprice(rs.getInt(3));
				c.setCdescription(rs.getString(4));
				c.setSeater(rs.getInt(5));
				c.setCimage(rs.getString(6));
				
				System.out.println("Inside while-2");
				list1.add(c);
				System.out.println(list1);				
			}
			}catch(Exception e) {System.out.println(e);}
		return list1;
	}
	

	
	public void addCar(Car car) {
		String query = "insert into cars values(" + car.getCarid()+ ","
											+ "'"+car.getCname()+"',"
											+ ""+car.getCprice()+","
											+ "'"+car.getCdescription()+"',"
											+ ""+car.getSeater()+","
											+ "'"+car.getCimage()+"')";
		System.out.println(query);
		try {
			Statement st = connection.createStatement();
			st.executeUpdate(query);
			System.out.println("Car INSERTED SUCCESSFULY!!\n\n"); 
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int deleteCar(int id) {
		int status = 0;
		String query = "delete from cars where carid ="+id;
		System.out.println(query);
		try {
			Statement st = connection.createStatement();
			status = st.executeUpdate(query);
			System.out.println(" Car Deleted SUCCESSFULY!!\n\n"); 
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return status;
	}
	

	public Car viewoneCar(int carid) {
		// TODO Auto-generated method stub
	System.out.println(list1);
	System.out.println(carid);
	System.out.println(list1.get(0).getCarid());
	
		for (Car c : list1) {
			if (c.getCarid() == carid) {
				System.out.println("carid is equal");
				System.out.println("------------"+list1);	
				return c;
			}
		}
	
		return null;

	}

	@Override
	public void updateCar(int custid,Car car) {
		System.out.println("in dao Impl");
		int noOfRecords = 0;
		try {
			PreparedStatement pst = connection.prepareStatement(
					"update cars set cname=?,cprice=?,cdescription=?,seater=?,cimage=? where carid=? ");
			
			pst.setString(1, car.getCname());
			pst.setInt(2, car.getCprice());
			pst.setString(3, car.getCdescription());
			pst.setInt(4, car.getSeater());
			pst.setString(5, car.getCimage());
			pst.setInt(6, car.getCarid());
			

			noOfRecords = pst.executeUpdate();
			System.out.println(noOfRecords + " Car record updated successfully");
		} catch (Exception e) {
		}
		
	}
}
