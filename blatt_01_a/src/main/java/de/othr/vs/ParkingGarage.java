package de.othr.vs;

import de.othr.vs.Car;

class ParkingGarage {

	int slots;
	int reserved;

	Object monitor;

	ParkingGarage(int slots) {
		this.slots = slots;
		this.reserved = 0;

		this.monitor = new Object();
	}

	void enter(Car c) throws InterruptedException {
		synchronized (this.monitor) {
			while (this.reserved >= this.slots) {
				System.out.println("  Waiting to enter " +
					c.getLicensePlate());
				this.monitor.wait();
			}
			this.reserved++;
		}
	}

	void leave(Car c) {
		synchronized (this.monitor) {
			if (this.reserved > 0) {
				reserved--;
				monitor.notify();
			}
		}
	}
}
