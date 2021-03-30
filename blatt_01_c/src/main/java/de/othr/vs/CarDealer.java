package de.othr.vs;

import java.util.Random;

import de.othr.vs.Car;
import de.othr.vs.Warehouse;

class CarDealer implements Runnable {

    private Warehouse warehouse;

    CarDealer(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                System.out.println("  Buy Car");
                Car c = this.warehouse.get();
                System.out.println("Bought " + c.getLicensePlate());
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
