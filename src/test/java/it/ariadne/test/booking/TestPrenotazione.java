package it.ariadne.test.booking;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.bookingresources.Car;
import it.ariadne.bookingresources.Reservation;
import it.ariadne.dao.DaoIF;
import junit.framework.Assert;

public class TestPrenotazione {

	Reservation res;
	DaoIF<Reservation> daoRes;

	@Before
	public void setup() {

		// gioco con i campi di

		// Car c = new Car("auto1", "Renault Clio", 5, "Petrol");

		res = new Reservation();
		res.setIdUser("user1");
		res.setDataStart(new DateTime());
		// System.out.println(res.getDataStart());
		res.setDataStop((new DateTime()).plusHours(6));
		// System.out.println(res.getDataStop());
		// System.out.println(res.getIntervallo());

		res.setIdReservation("reservation1");

		int ore = res.getIntervallo().toPeriod().getHours();
		// System.out.println("differenza ore\t"+ore);

		DateTime d1 = new DateTime().plusMinutes(10);
		DateTime d2 = d1.plusHours(5);
		Interval int2 = new Interval(d1, d2);
		System.out.println(int2);

		// if(res.getIntervallo().overlaps(int2)){
		// System.out.println("si sovrappongono");
		//
		// }else{
		// System.out.println("non si sovrappongono");
		// }

	}

	@Test
	public void testDaoPrenotazione() {
		daoRes.add(new Reservation("user1", "auto1", "user1auto1#1", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2)));
		daoRes.add(new Reservation("user1", "auto1", "user1auto1#2", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2)));
		daoRes.add(new Reservation("user1", "auto2", "user1auto2#1", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2)));
		daoRes.add(new Reservation("user2", "auto1", "user2auto1#1", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2)));
		daoRes.add(new Reservation("user2", "auto1", "user2auto1#1", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2)));
		//res = new Reservation("user2", "auto2", "user2auto2#1", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2));
		
		//Assert.assertEquals(message, expected, actual);

	}

}
