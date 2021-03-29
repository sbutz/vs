package de.othr.vs;

import de.othr.vs.CarDealer;
import de.othr.vs.CarProducer;
import de.othr.vs.Warehouse;

class Simulation {

	public static void main(String[] args) throws InterruptedException {
		Warehouse warehouse = new Warehouse(10);

		for (int i = 0; i < 1; i++) {
			Thread t = new Thread(new CarProducer(warehouse));
			t.setDaemon(true);
			t.start();
		}

		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(new CarDealer(warehouse));
			t.setDaemon(true);
			t.start();
		}

		Thread.sleep(30000);
		System.out.println("End of Simulation");
	}
}
