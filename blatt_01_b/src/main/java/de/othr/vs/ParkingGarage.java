package de.othr.vs;

import de.othr.vs.Car;

class ParkingGarage {

	int slots;
	int minSlots;
	int reserved;

	Object monitor;

	ParkingGarage(int slots, int minSlots) {
		this.slots = slots;
		this.minSlots = minSlots;
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
			this.monitor.notifyAll();
		}
	}

	void leave(Car c) throws InterruptedException {
		synchronized (this.monitor) {
			while (this.reserved <= this.minSlots) {
				System.out.println("  Waiting to leave " +
					c.getLicensePlate());
				this.monitor.wait();
			}
			this.reserved--;
			this.monitor.notifyAll();
		}
	}
}
