package it.ariadne.test.booking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import it.ariadne.bookingresources.Car;
import it.ariadne.controller.Controller;
import it.ariadne.dao.DaoCar;

public class TestController {

	Car auto1;
	Car auto2;
	DaoCar dao;
	Controller<Car> contCar;

	@Before
	public void setup() {

		contCar = new Controller<Car>(new DaoCar());

		auto1 = new Car();
		auto1.setId("1auto");
		auto1.setDescription("Alfa Rome 156");
		auto1.setNumberOfSpace(4);
		auto1.setType("PETROL");
		auto2 = new Car("2auto", "Lamborighini", 2, "PETROL");

	}

	@Test
	public void testAdd() {// non è possibile inserire instanze che non siano di
							// tipo Car

		Assert.assertTrue("Carico correttamente un Car: " + auto1.getId(), contCar.add(auto1));
		Assert.assertTrue("Carico correttamente un Car: " + auto2.getId(), contCar.add(auto2));
		Assert.assertFalse("Cerco di caricare un oggetto Car già inserito " + auto1.getId(), contCar.add(auto1));

	}

	@Test
	public void testGetAll() {

		int i = 0;
		contCar.add(auto1);
		i++;
		contCar.add(auto2);
		i++;

//		Assert.assertTrue("Il numero di elementi e pari a ", i == contCar.listAll().size());
		Assert.assertEquals("Il numero di elementi e pari a ", i, contCar.listAll().size());

		contCar.add(auto2);
		i++;
//		Assert.assertFalse("Il numero di elementi e pari a ", i <= contCar.listAll().size());
		Assert.assertNotEquals("Il numero di elementi e pari a ", i, contCar.listAll().size());


		// TreeMap<String, Car> po = contCar.listAll();
		// for (Map.Entry<String, Car> entry : po.entrySet()) {
		//
		// System.out.println(entry.getValue().getId() + "\t" +
		// entry.getValue().getDescription() + "\t"
		// + entry.getValue().getNumberOfSpace() + "\t" +
		// entry.getValue().getType());
		//
		// }

	}

	@Test
	public void testDelete() {
		contCar.add(auto1);
		contCar.add(auto2);

		Assert.assertTrue("La risorsa è stata rimossa", contCar.delete(auto1));
		Assert.assertTrue("La risorsa è stata rimossa", contCar.delete(auto2));
		Assert.assertFalse("La risorsa non è presente non è possibile rimuoverla", contCar.delete(auto1));
		Assert.assertFalse("La risorsa non è presente non è possibile rimuoverla", contCar.delete(auto2));
		Assert.assertFalse("La risorsa non è presente non è possibile rimuoverla",
				contCar.delete(new Car("pluto", null, 3, null)));

	}

	@Test
	public void testGetById() {

		contCar.add(auto1);
		contCar.add(auto2);
		String idAuto = auto1.getId();
		String idAutoFalsa = "falso";
		Car esempio1 = contCar.getById(idAuto);
		Car esempio2 = contCar.getById(idAutoFalsa);

		Assert.assertTrue("Auto trovata con successo!", esempio1.getId().equals(idAuto));
//		Assert.assertTrue("Auto trovata con successo!", esempio1.getDescription().equals(auto1.getDescription()));
//		Assert.assertTrue("Auto trovata con successo!", esempio1.getNumberOfSpace() == auto1.getNumberOfSpace());
//		Assert.assertTrue("Auto trovata con successo!", esempio1.getType().equals(auto1.getType()));
//		Assert.assertFalse("Auto non trovata con successo!", esempio2.getId().equals(idAutoFalsa));
//		Assert.assertFalse("Auto non trovata con successo!", esempio1.getDescription() != null);
//		Assert.assertFalse("Auto non trovata con successo!", esempio1.getNumberOfSpace() != 0);
//		Assert.assertFalse("Auto non trovata con successo!", esempio1.getType() != null);
//
//		// Assert.assertTrue("Auto presente viene
//		// restituita",contCar.getById(idAuto)!= null);
//		Assert.assertFalse("Auto presente viene restituita", contCar.getById("") == null);

	}

	@Test
	public void testUpdate() {

		contCar.add(auto1);
		contCar.add(auto2);

		Car autoModificata = new Car(auto1.getId(), "Mercedes", 55, "Letame");
		Assert.assertTrue("La modifica viene fatta con successo", contCar.update(autoModificata));

		Assert.assertFalse("I campi sono diversi", (contCar.getById(auto1.getId()).getType().equals("Mercedes")));
		Assert.assertEquals("Non viene fatto l'update su un oggetto non presente",contCar.getById("falso") ,null);
//		Assert.assertFalse("Non viene fatto l'update su un oggetto non presente", contCar.getById("falso") != null);

	}

}
