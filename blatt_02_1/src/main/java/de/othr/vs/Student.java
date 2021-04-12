package de.othr.vs;

import java.util.Random;

import de.othr.vs.KitchenCounter;

public class Student implements Runnable {
    private String name;
    private KitchenCounter kitchen;

    public Student(KitchenCounter kitchen, String name) {
        this.kitchen = kitchen;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(10) * 1000);
                this.kitchen.take();
                System.out.println("Semmel bekommen.");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
