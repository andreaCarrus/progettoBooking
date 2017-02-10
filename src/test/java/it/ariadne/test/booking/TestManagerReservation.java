package it.ariadne.test.booking;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.businessogic.ReservationMngmt;

public class TestManagerReservation {

	ReservationMngmt rm;

	@Before
	public void setup() {
		rm = new ReservationMngmt("Manager Reservation");

	}

	@Test
	public void testAddManagerResource() {

		Assert.assertTrue("aggiungo una reservation nel sistema", rm.addReservation("utente1", "risorsa1",
				"prenotazione1", new DateTime(), new DateTime().plusHours(4), new DateTime().plusHours(6)));

		Assert.assertTrue("aggiungo una reservation nel sistema", rm.addReservation("utente2", "risorsa2",
				"prenotazione2", new DateTime(), new DateTime().plusHours(4), new DateTime().plusHours(6)));

		Assert.assertTrue("aggiungo una reservation nel sistema", rm.addReservation("utente3", "risorsa3",
				"prenotazione3", new DateTime(), new DateTime().plusHours(4), new DateTime().plusHours(6)));

		Assert.assertFalse("aggiungo una reservation nel sistema", rm.addReservation("utente1", "risorsa1",
				"prenotazione1", new DateTime(), new DateTime().plusHours(4), new DateTime().plusHours(6)));
	}

	@Test
	public void testGetByIdResource() {

		testAddManagerResource();

		Assert.assertNotEquals("restituisco una risorsa presente", rm.getReservationById("prenotazione1"), null);
		Assert.assertEquals("restituisco una risorsa non presente", rm.getReservationById("pazione1"), null);

	}

	@Test
	public void testListAllResource() {

		testAddManagerResource();
		Assert.assertEquals("Controllo se sono presenti il numero corretto di prenotazioni", 3, rm.getListAll().size());
	}

	@Test
	public void testDeleteResource() {

		testAddManagerResource();
		Assert.assertTrue("Eliminazione di una Reservation presente", rm.deleteReservation("prenotazione1"));
		Assert.assertFalse("Eliminazione din una Reservation non presente", rm.deleteReservation("prenotazione1"));
		Assert.assertFalse("Eliminazione din una Reservation non presente", rm.deleteReservation("prenzione1"));

	}

	@Test
	public void testUpdateResource() {

		testAddManagerResource();
		DateTime d1 = rm.getReservationById("prenotazione1").getDataStart();
		DateTime d2 = rm.getReservationById("prenotazione1").getDataStop();
		// System.out.println("Esempio"+d1+"\t"+d2);

		DateTime ds = new DateTime();
		DateTime dst = new DateTime().plusHours(5);

		Assert.assertTrue("Modifica di un Reservation presente ",
				rm.updateReservation("utente1", "risorsa1", "prenotazione1", new DateTime(), ds, dst));

		// System.out.println(rm.getReservationById("prenotazione1").getDataStart());
		Assert.assertFalse("Controllo che la modifica venga eseguita",
				d1.equals(rm.getReservationById("prenotazione1").getDataStart()));

	}

}
