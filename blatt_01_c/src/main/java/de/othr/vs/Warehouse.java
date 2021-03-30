package de.othr.vs;

import java.util.PriorityQueue;

import de.othr.vs.Car;

class Warehouse {

    private int capacity;
    private PriorityQueue<Car> cars;
    private Object monitor;

    Warehouse(int capacity) {
        this.capacity = capacity;
        this.cars = new PriorityQueue<Car>(capacity);
        this.monitor = new Object();
    }

    void add(Car c) {
        synchronized (this.monitor) {
            while (this.cars.size() >= this.capacity) {
                try {
                    System.out.println("Waiting to insert cars.");
                    this.monitor.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }

            this.cars.add(c);
            this.monitor.notifyAll();
        }
    }

    Car get() {
        synchronized (this.monitor) {
            while (this.cars.isEmpty()) {
                try {
                    System.out.println("Waiting for more cars.");
                    this.monitor.wait();
                } catch (InterruptedException e) {
                    break;
                }
            }

            Car c = this.cars.poll();
            this.monitor.notifyAll();
            return c;
        }
    }
}
