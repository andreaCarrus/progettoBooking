package it.ariadne.test.booking;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.bookingresources.Car;
import it.ariadne.businessogic.ResourceMngmt;

public class TestResourceManager {

	ResourceMngmt rm;
	Map<String, Car> mappaAuto = null;

	@Before
	public void setup() {

		rm = new ResourceMngmt("Manager delle risorse");// deve prendere in
														// ingresso qualche
														// altro parametro??

	}

	@Test
	public void testAddManagementCar() {

		for (int i = 0; i < 10; i++) {
			Assert.assertTrue("Caricamento delle automobili riuscito",
					rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina"));
		}

		for (int i = 0; i < 10; i++) {
			Assert.assertFalse("Caricamento di auto già presenti, non è possibile",
					rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina"));
		}

	}

	@Test
	public void testGetAllCar() {

		for (int i = 0; i < 10; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina");
		}

		Assert.assertEquals("Controllo inserimento numero corretto di elementi", rm.getAllCar().size(), 10);

		rm.addCar("auto" + 5, "automobile numero: " + 5, 4, "benzina");

		Assert.assertNotEquals("Controllo che non venga agginto un elemento già presente", rm.getAllCar().size(), 11);

		mappaAuto = rm.getAllCar();

		// for (Map.Entry<String, Car> entry : mappaAuto.entrySet()) {

		// System.out.println(entry.getValue().getId() + "\t" +
		// entry.getValue().getDescription() + "\t"
		// + entry.getValue().getNumberOfSpace() + "\t" +
		// entry.getValue().getType());

		// }

	}

	@Test
	public void testUpdateManagementCar() {

		// inizializzo la lista

		for (int i = 0; i < 10; i++) {
			Assert.assertTrue("Caricamento delle automobili riuscito",
					rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina"));
		}

		Assert.assertTrue("Modifica di una risorsa presente", rm.updateCar("auto0", "pluto", 99, null));// modifico

		// controllo della modifica
		Assert.assertEquals("Controllo numero di posto", 99, rm.getAllCar().get("auto0").getNumberOfSpace());
		Assert.assertTrue(rm.getAllCar().get("auto0").getDescription().equals("pluto"));
		Assert.assertEquals("controlo del tipo", rm.getAllCar().get("auto0").getType(), null);

		Assert.assertFalse("Modifica di una risorsa non presente", rm.updateCar("auto14", "pluto", 55, "spalshs"));

	}

	@Test
	public void testGetCarById() {

		String id = "auto1";
		String idFake = "autoFake";
		Car c;

		for (int i = 0; i < 10; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina");
		}

		c = rm.getCarById(id);

		Assert.assertNotEquals("Controllo che sia presente la macchina", c, null);

		c = rm.getCarById(idFake);
		Assert.assertEquals("Controllo che non mi dia una macchina non presente", c, null);

	}

	@Test
	public void testDeleteCar() {

		String id = "autoAdd";
		String idFake = "autoFake";
		Car c;

		for (int i = 0; i < 10; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina");
		}

		rm.addCar(id, "non so che scrivere", 44, "gatti");

		Assert.assertTrue("Elimino una macchina presente", rm.deleteCar(id));

		Assert.assertFalse("Elimino una macchina precedentemente eliminata", rm.deleteCar(id));
		Assert.assertFalse("Elimino una macchina precedentemente non presente", rm.deleteCar(idFake));

	}

	@Test
	public void testCarSearchWithSpaceValue() {

		List<Car> listaTrovata;

		for (int i = 0; i < 10; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina");
		}

		for (int i = 10; i < 20; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 5, "benzina");
		}

		listaTrovata = rm.getCarWithSpace(4);
		Assert.assertEquals("Trovato numero di elementi con 4 posti corretto", listaTrovata.size(), 10);

		rm.deleteCar("auto8");
		listaTrovata = rm.getCarWithSpace(4);
		Assert.assertEquals("Trovato numero di elementi con 4 posti corretto", listaTrovata.size(), 9);

		listaTrovata = rm.getCarWithSpace(2);
		Assert.assertEquals("Trova numero di macchine con un numero di posti non esistente", 0, listaTrovata.size());

	}

	@Test
	public void testCarSearchAlmostSpaceValue() {

		List<Car> listaTrovata;

		for (int i = 0; i < 10; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 4, "benzina");
		}

		for (int i = 10; i < 20; i++) {
			rm.addCar("auto" + i, "automobile numero: " + i, 5, "benzina");
		}

		listaTrovata = rm.getCarAtLeastSpace(4);
		Assert.assertEquals("Trovato numero di elementi con 4 posti corretto", listaTrovata.size(), 20);

		rm.deleteCar("auto8");
		listaTrovata = rm.getCarAtLeastSpace(4);
		Assert.assertEquals("Trovato numero di elementi con 4 posti corretto", listaTrovata.size(), 19);

		listaTrovata = rm.getCarAtLeastSpace(6);
		Assert.assertEquals("Trova numero di macchine con un numero di posti non esistente", 0, listaTrovata.size());
		
		listaTrovata = rm.getCarAtLeastSpace(2);
		Assert.assertEquals("Trova numero di macchine con un numero di posti non esistente", 19, listaTrovata.size());

	}

}
