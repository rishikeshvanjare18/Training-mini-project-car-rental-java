package com.rishi.Car.Rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rishi.Car.Rental.DAO.CarDAO;
import com.rishi.Car.Rental.model.Car;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class CarController {
	
	@Autowired
	private CarDAO carDao;
	
	@GetMapping("/Car")
	public List getCars() {	
	return carDao.viewAllCars();
	}
	
	@GetMapping("/Car/{carid}")
	public ResponseEntity getonecar(@PathVariable("carid") int carid) {
		Car car=carDao.viewoneCar(carid);
		
		if (car == null) {
			return new ResponseEntity("No Car found for carid " + carid, HttpStatus.NOT_FOUND);
		}
		System.out.println("new name="+car.getCname());
		return new ResponseEntity(car, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/post/Car")
	public ResponseEntity addCar(@RequestBody Car car) {
		carDao.addCar(car);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/deleteCar/{carid}")
	public ResponseEntity deleteCar(@PathVariable("carid") int id) {		
		int flag=carDao.deleteCar(id);			
		
		if(flag==0) {
			return new ResponseEntity("Error delete",HttpStatus.OK);
		}
		return new ResponseEntity("Cid:"+ id +"car Record deleted successfully",HttpStatus.OK);
	}
	
	
	@PutMapping(value = "/update/Car/{id}") 
	public ResponseEntity updateCar(@PathVariable("id") int id,@RequestBody Car car)
	{	
		System.out.println("inside update controller");
		System.out.println("in controller");
		carDao.updateCar(id,car);
		return new ResponseEntity(car,HttpStatus.OK);
	}
	
	
}
