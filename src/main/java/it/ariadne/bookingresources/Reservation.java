package it.ariadne.bookingresources;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;

public class Reservation {
	
	private String idUser;
	private String idResource;
	private String idReservation;
	
	private DateTime dataReservation;
	private DateTime dataStart;
	private DateTime dataStop;
	
	private Interval intervallo;

	public Reservation(String idUser, String idResource, String idReservation, DateTime dataReservation, DateTime dataStart,
			DateTime dataStop) {
		super();
		this.idUser = idUser;
		this.idResource = idResource;
		this.idReservation = idReservation;
		this.dataReservation = dataReservation;
		this.dataStart = dataStart;
		this.dataStop = dataStart;
		
		intervallo = new Interval(dataStart, dataStart);
	}
	
	public Reservation(){
		
	}
	

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(String idReservation) {
		this.idReservation = idReservation;
	}

	public DateTime getDataReservation() {
		return dataReservation;
	}

	public void setDataReservation(DateTime dataReservation) {
		this.dataReservation = dataReservation;
	}

	public DateTime getDataStart() {
		return dataStart;
	}

	public void setDataStart(DateTime dataStart) {
		this.dataStart = dataStart;
	}

	public DateTime getDataStop() {
		return dataStop;
	}

	public void setDataStop(DateTime dataStop) {
		this.dataStop = dataStop;
	}

	public Interval getIntervallo() {
		return new Interval(dataStart, dataStop);
	}

	public void setIntervallo(Interval intervallo) {
		this.intervallo = intervallo;
	}

	public String getIdResource() {
		return idResource;
	}

	public void setIdResource(String idResource) {
		this.idResource = idResource;
	}
	
	

}
