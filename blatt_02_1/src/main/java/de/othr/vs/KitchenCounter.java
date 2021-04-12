package de.othr.vs;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {
    private int capacity;
    private int counter = 0;
    private Lock monitor = new ReentrantLock();
    private Condition producer = monitor.newCondition();
    private Condition consumer = monitor.newCondition();

    public KitchenCounter(int capacity) {
        this.capacity = capacity;
    }

    public void put() {
        this.monitor.lock();
        try {
            while (this.counter >= this.capacity) {
                try {
                    System.out.println("Theke ist voll");
                    this.producer.await();
                } catch (InterruptedException e) {
                }
            }
            this.counter++;
            this.consumer.signal();
            return;
        } finally {
            this.monitor.unlock();
        }
    }

    public void take() {
        this.monitor.lock();
        try {
            while (this.counter <= 0) {
                try {
                    System.out.println("Theke ist leer");
                    this.consumer.await();
                } catch (InterruptedException e) {
                }
            }
            this.counter--;
            this.producer.signal();
            return;
        } finally {
            this.monitor.unlock();
        }
    }
}
