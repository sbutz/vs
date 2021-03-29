package de.othr.vs;

import java.util.Random;

import de.othr.vs.Car;
import de.othr.vs.Warehouse;

class CarProducer implements Runnable {

	private Warehouse warehouse;

	CarProducer(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			try {
				Thread.sleep(new Random().nextInt(10) * 1000);
				Car c = new Car("R-FH-" + i++);
				System.out.println("Insert new car " +
					c.getLicensePlate());
				this.warehouse.add(c);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
