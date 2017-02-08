package it.ariadne.test.booking;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.bookingresources.Car;
import it.ariadne.dao.DaoCar;
import it.ariadne.dao.DaoIF;

public class TestDaoCar {

	Car auto;
	Car auto1;
	Car auto2;
	DaoIF<Car> dri;
	Map<String, Car> mappaAuto;

	@Before
	public void setup() {

		auto1 = new Car();
		auto1.setId("1auto");
		auto1.setDescription("Alfa Rome 156");
		auto1.setNumberOfSpace(4);
		auto1.setType("PETROL");
		auto2 = new Car("2auto", "Lamborighini", 2, "PETROL");
		dri = new DaoCar();

		dri.add(auto1);
		dri.add(auto2);

		auto = new Car("3auto", "Lancia Delta", 4, "PETROL");

	}

	@Test
	public void testCreazioneRisorse() {
		// dri.add(auto1);
		// dri.add(auto2);

		mappaAuto = dri.listAll();

		Assert.assertTrue("Dimensione della mappa corretta", 2 == mappaAuto.size());

		// for (Map.Entry<String, Car> entry : mappaAuto.entrySet()) {
		//
		// System.out.println(entry.getValue().getId() + "\t" +
		// entry.getValue().getDescription() + "\t"
		// + entry.getValue().getNumberOfSpace() + "\t" +
		// entry.getValue().getType());
		//
		// }

	}

	@Test
	public void testInserimento() {

		Assert.assertTrue("La prima auto viene caricata", dri.add(auto));
		Assert.assertFalse("La seconda auto non viene caricata", dri.add(auto));

	}

	@Test
	public void testEliminazione() {

		dri.add(auto);
		// Map<String, Car> mappaAuto = dri.listAll();

		Assert.assertTrue("Eliminazione dell'automobile" + auto.getId(), dri.delete(auto));
		Assert.assertFalse("Impossibile eliminare un auto già precedentemente eliminata", dri.delete(auto));

		Assert.assertFalse("Eliminazione di un'auto non presente non consentita",
				dri.delete(new Car("aaaa", null, 100, null)));

	}

	@Test
	public void testUpdate() {

		dri.add(auto);// aggiungo auto3

		Car autoSave = (Car) dri.listAll().get(auto.getId());
		Car autoUpdate = new Car(autoSave.getId(), "Auto numero 4", 6, "DIESEL");

		Assert.assertTrue("Aggiornamento riuscito", dri.update(autoUpdate));
		Assert.assertFalse("Aggiornamento di una Car che non esiste", dri.update(new Car("xxxx", null, 0, null)));

	}

	@Test
	public void testGetById() {

		dri.add(auto);
		mappaAuto = dri.listAll();

//		for (Map.Entry<String, Car> entry : mappaAuto.entrySet()) {
//
//			System.out.println(entry.getValue().getId() + "\t" + entry.getValue().getDescription() + "\t"
//					+ entry.getValue().getNumberOfSpace() + "\t" + entry.getValue().getType());
//
//		}
		// System.out.println(dri.getById(auto.getId())+"\t: "+auto.getId());
		Car c =  dri.getById(auto.getId());
		Assert.assertTrue("Ricerca dell'elemento corretto", c.getId().equals(auto.getId()));
		
		c =  dri.getById("errore");
		Assert.assertFalse("Non deve essere presente il veicolo",c !=null);

	}

}
