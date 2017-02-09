package it.ariadne.businessogic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import it.ariadne.bookingresources.Car;
import it.ariadne.businessogic.interfacce.CarManagementIF;
import it.ariadne.controller.Controller;
import it.ariadne.dao.DaoCar;

public class ResManager implements CarManagementIF {

	private String nomeManager;
	private Controller<Car> controllerCar;

	public ResManager(String nomeManager) {
		this.nomeManager = nomeManager;
		controllerCar = new Controller<Car>(new DaoCar());
	}

	public boolean addCar(String id, String description, int numberOfSpace, String type) {
		return controllerCar.add(new Car(id, description, numberOfSpace, type));

	}

	public TreeMap<String, Car> getAllCar() {// da preferire il set
		return controllerCar.listAll();

	}

	public boolean updateCar(String id, String description, int numberOfSpace, String type) {
		return controllerCar.update(new Car(id, description, numberOfSpace, type));

	}

	public boolean deleteCar(String id) {

		// return controllerCar.delete(new Car(id, null, 0, null));

		Car carToDelete = getCarById(id);

		if (carToDelete != null) {
			return controllerCar.delete(carToDelete);
		}
		return false;

	}

	public Car getCarById(String id) {
		return controllerCar.getById(id);
	}

	public ArrayList<Car> getCarAtLeastSpace(int numberOfSpace) {

		ArrayList<Car> listaAuto = new ArrayList<Car>();
		TreeMap<String, Car> mappa = getAllCar();

		for (Map.Entry<String, Car> entry : mappa.entrySet()) {
			if (entry.getValue().getNumberOfSpace() >= numberOfSpace) {
				listaAuto.add(entry.getValue());
			}
		}

		return listaAuto;
	}

	public ArrayList<Car> getCarWithSpace(int numberOfSpace) {

		ArrayList<Car> listaAuto = new ArrayList<Car>();
		TreeMap<String, Car> mappa = getAllCar();

		for (Map.Entry<String, Car> entry : mappa.entrySet()) {
			if (entry.getValue().getNumberOfSpace() == numberOfSpace) {
				listaAuto.add(entry.getValue());
			}
		}

		return listaAuto;
	}

}
