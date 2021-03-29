package de.othr.vs;

import java.util.Random;

import de.othr.vs.ParkingGarage;

class Car implements Runnable {

	String licensePlate;
	ParkingGarage garage;

	Car(String licensePlate, ParkingGarage garage) {
		this.licensePlate = licensePlate;
		this.garage = garage;
	}

	String getLicensePlate() {
		return this.licensePlate;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(new Random().nextInt(10) * 1000);
				this.garage.enter(this);
				System.out.println("Enter " + this.licensePlate);

				Thread.sleep(new Random().nextInt(10) * 1000);
				this.garage.leave(this);
				System.out.println("Exit " + this.licensePlate);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
}
