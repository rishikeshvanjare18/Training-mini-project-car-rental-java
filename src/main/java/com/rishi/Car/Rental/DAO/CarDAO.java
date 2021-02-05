package com.rishi.Car.Rental.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.rishi.Car.Rental.model.Car;

@Repository
public interface CarDAO {
	public List<Car> viewAllCars();
	public void updateCar(int carid,Car car);
	public void addCar(Car car);
	public int deleteCar(int carid);
	public  Car viewoneCar(int carid);
}
