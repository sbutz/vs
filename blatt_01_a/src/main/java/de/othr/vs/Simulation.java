package de.othr.vs;

import de.othr.vs.ParkingGarage;

class Simulation {
	Simulation() throws InterruptedException {
		ParkingGarage garage = new ParkingGarage(5);

		for (int i = 0; i < 20; i++) {
			Thread t = new Thread(new Car("R-FH-" + i, garage));
			t.setDaemon(true);
			t.start();
		}

		Thread.sleep(10000);
		System.out.println("Ende der Simulation");
	}
}
