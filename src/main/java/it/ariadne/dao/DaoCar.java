package it.ariadne.dao;

import java.util.TreeMap;

import it.ariadne.bookingresources.Car;

public class DaoCar implements DaoIF<Car> {

	private TreeMap<String, Car> carMap;

	public DaoCar() {
		carMap = new TreeMap<String, Car>();
	}

	public TreeMap<String, Car> listAll() {
		return carMap;
	}

	public boolean update(Car t) {

		// System.out.println("Ricerca di aggiornare:"+t.getId());
		if (carMap.containsKey(t.getId())) {
			carMap.put(t.getId(), t);// aggiornamento riuscito
			// System.out.println("Aggiornamento riuscito!");
			return true;

		}
		return false;
	}

	public boolean delete(Car t) {

		if (carMap.containsKey(t.getId())) {
			carMap.remove(t.getId());
			return true;
		}
		return false;
	}

	public boolean add(Car t) {

		if (!carMap.containsKey(t.getId())) {
			carMap.put(t.getId(), t);
			
			return true;
		}
		return false;
	}

	public Car getById(String id) {
		
		if(carMap.containsKey(id)){
			//System.out.println("è presente l'id!!");
			return carMap.get(id);
		}
		
		return null;
	}

}
