package it.ariadne.test.booking;

import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.bookingresources.Reservation;
import it.ariadne.dao.DaoIF;
import it.ariadne.dao.DaoReservation;

public class TestPrenotazione {

	Reservation res;
	DaoIF<Reservation> daoRes;

	@Before
	public void setup() {

		daoRes = new DaoReservation();

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
		// System.out.println(int2);

		// if(res.getIntervallo().overlaps(int2)){
		// System.out.println("si sovrappongono");
		//
		// }else{
		// System.out.println("non si sovrappongono");
		// }

	}

	@Test
	public void testDaoPrenotazione() {

		// System.out.println("Sto fuori!");

		Assert.assertTrue("Aggiungo una prenotazione", daoRes.add(new Reservation("user1", "auto1", "user1auto1#2",
				new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2))));
		Assert.assertTrue("Aggiungo una prenotazione", daoRes.add(new Reservation("user1", "auto2", "user1auto2#1",
				new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2))));
		Assert.assertTrue("Aggiungo una prenotazione", daoRes.add(new Reservation("user2", "auto1", "user2auto1#1",
				new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2))));
		Assert.assertTrue("Aggiungo una prenotazione", daoRes.add(new Reservation("user2", "auto2", "user2auto2#1",
				new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2))));

		Assert.assertFalse("Aggiungo una prenotazione già presente", daoRes.add(new Reservation("user2", "auto2",
				"user2auto2#1", new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2))));

	}

	@Test
	public void testReservationGetAll() {
		testDaoPrenotazione();//

		Map<String, Reservation> mappaReservation = daoRes.listAll();

		// for (Map.Entry<String, Reservation> entry :
		// mappaReservation.entrySet()) {
		//
		// System.out.println(entry);
		//
		// }

		Assert.assertEquals("Controllo dimensione della mappa", 4, mappaReservation.size());
		Assert.assertTrue("Aggiungo una prenotazione", daoRes.add(new Reservation("user3", "auto3", "user3auto2#1",
				new DateTime(), new DateTime().plusDays(1), new DateTime().plusDays(2).plusHours(2))));

		mappaReservation = daoRes.listAll();
		Assert.assertEquals("Controllo dimensione della mappa", 5, mappaReservation.size());

	}

	@Test
	public void testGetReservation() {

		testDaoPrenotazione();//

		Reservation res1 = daoRes.getById("user1auto1#2");
		Assert.assertNotEquals("ho trovato la reservetion", null, res1);

		Assert.assertEquals("cerco una reservation non present", null, daoRes.getById("plutopippo"));

	}

	@Test
	public void testUpdateReservation() {
		
		testDaoPrenotazione();
		
		Reservation res1 = daoRes.getById("user1auto1#2");
		Reservation resFake = new Reservation(null, null, "idPrenotazione", new DateTime(), new DateTime(), new DateTime().plusMinutes(600));
		String idPersona = res1.getIdUser();
		res1.setIdUser("pippo");//modifico l'user
		
		Assert.assertTrue("Modifico una Reservation presente", daoRes.update(res1));
		Assert.assertFalse("Cerco di modificare una reservation non presente", daoRes.update(resFake));
		
		Assert.assertNotEquals("Controllo che la modifica venga fatta correttamente", idPersona, daoRes.getById("user1auto1#2"));		
		

	}
	
	@Test
	public void testDeleteReservation(){
		
		testDaoPrenotazione();
		
		int numeroElementi = daoRes.listAll().size();
		Reservation rewww = daoRes.getById("user1auto1#2");
		Reservation resFake = new Reservation("", "", "", null, null, null);
		
		//provo ad eliminare un oggetto
		
		Assert.assertTrue("eliminazione di una Reservation", daoRes.delete(rewww));
		Assert.assertFalse("eliminazione di una Reservation appena eliminata", daoRes.delete(rewww));
		Assert.assertFalse("eliminazione di una Reservation", daoRes.delete(resFake));
		
		Assert.assertNotEquals("Ho eliminato una Reservation mi aspetto che il numero di elementi sia diverso", numeroElementi, daoRes.listAll().size());
		
		
	}

}
