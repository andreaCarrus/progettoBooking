package it.ariadne.businessogic;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import it.ariadne.bookingresources.Reservation;
import it.ariadne.controller.Controller;
import it.ariadne.dao.DaoReservation;

public class ReservationMngmt {

	private String nomeMngmt;
	private Controller<Reservation> controllerReservation;

	public ReservationMngmt(String nomeMngmt) {

		this.nomeMngmt = nomeMngmt;

		controllerReservation = new Controller<Reservation>(new DaoReservation());
	}

	public boolean addReservation(String userId, String resourceId, String reservationId, DateTime dataReservation,
			DateTime dataStart, DateTime dataStop) {
		
		// System.out.println("Reservation in corso...");

		if (overlapControl(resourceId, dataStart, dataStop)) {
			return controllerReservation
					.add(new Reservation(userId, resourceId, reservationId, dataReservation, dataStart, dataStop));

		}
		return false;

	}

	public Reservation getReservationById(String idPrenotazione) {
		// System.out.println("restituisco una reservation");

		return controllerReservation.getById(idPrenotazione);
	}

	public TreeMap<String, Reservation> getListAll() {
		return controllerReservation.listAll();
	}

	public boolean deleteReservation(String idPrenotazione) {

		if (controllerReservation.getById(idPrenotazione) != null) {
			return controllerReservation.delete(controllerReservation.getById(idPrenotazione));
		}

		return false;
	}

	public boolean updateReservation(String idUser, String idResource, String stringReservation,
			DateTime dateReservation, DateTime dateStart, DateTime dateStop) {
		
		if (overlapControl(idResource, dateStart, dateStop)) {
			return controllerReservation
					.update(new Reservation(idUser, idResource, stringReservation, dateReservation, dateStart, dateStop));

		}
		return false;

		// System.out.println("Aggiornamento di una reservation");

		
	}

	public ArrayList<Reservation> getReservationByResource(String idResource) {

		// Creare una mappa per rendere le operazioni di ricerca più veloci

		if (getListAll().containsKey(idResource)) {

			ArrayList<Reservation> listaReservation = new ArrayList<Reservation>();
			Map<String, Reservation> map = controllerReservation.listAll();

			for (Map.Entry<String, Reservation> entry : map.entrySet()) {
				if (entry.getValue().getIdResource().equals(idResource)) {
					listaReservation.add(entry.getValue());
				}

			}

			return listaReservation;
		}

		return null;

	}

	public boolean overlapControl(String idResource, DateTime dateStart, DateTime dateStop) {

		ArrayList<Reservation> listaReservation = getReservationByResource(idResource);
		
		if(listaReservation == null){
			return true;
		}
	
		Interval intIn = new Interval(dateStart, dateStop);
		
//		System.out.println("Overlap control...");
		
		for (Reservation reservation : listaReservation) {
			if (intIn.overlaps(new Interval(reservation.getDataStart(), reservation.getDataStop()))) {
				return false;
			}

		}

		return true;// non sono presenti overlap

	}

}
