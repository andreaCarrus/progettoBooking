package it.ariadne.dao;

import java.util.TreeMap;

import it.ariadne.bookingresources.Reservation;

public class DaoReservation implements DaoIF<Reservation> {

	private TreeMap<String, Reservation> reservationMap;

	public DaoReservation() {
		reservationMap = new TreeMap<String, Reservation>();
	}

	public TreeMap<String, Reservation> listAll() {
		return reservationMap;
	}

	public Reservation getById(String id) {
		if (reservationMap.containsKey(id)) {
			return reservationMap.get(id);
		}
		return null;
	}

	public boolean update(Reservation t) {
		if (reservationMap.containsKey(t.getIdReservation())) {
			reservationMap.put(t.getIdReservation(), t);
			return true;
		}
		return false;
	}

	public boolean delete(Reservation t) {
		if (t != null && reservationMap.containsKey(t.getIdReservation())) {
			reservationMap.remove(t.getIdReservation());
			return true;
		}
		return false;
	}

	public boolean add(Reservation t) {

		// System.out.println("Sto aggiungendo!");

		if (!reservationMap.containsKey(t.getIdReservation())) {
			reservationMap.put(t.getIdReservation(), t);
			return true;
		}
		return false;
	}

}
