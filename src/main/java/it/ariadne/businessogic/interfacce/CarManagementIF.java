package it.ariadne.businessogic.interfacce;

import java.util.List;
import java.util.TreeMap;

import it.ariadne.bookingresources.Car;

public interface CarManagementIF {

	public boolean addCar(String id, String description, int numberOfSpace, String type);//

	public boolean updateCar(String id, String description, int numberOfSpace, String type);//

	public TreeMap<String, Car> getAllCar();//

	public boolean deleteCar(String id);//

	public Car getCarById(String id);//

	public List<Car> getCarAtLeastSpace(int numberOfSpace);

	public List<Car> getCarWithSpace(int numberOfSpace);

}
