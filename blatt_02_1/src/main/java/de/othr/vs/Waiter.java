package de.othr.vs;

import java.util.Random;

import de.othr.vs.KitchenCounter;

public class Waiter implements Runnable {
    private KitchenCounter kitchen;
    private String name;

    public Waiter(KitchenCounter kitchen, String name) {
        this.kitchen = kitchen;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(new Random().nextInt(4) * 1000);

                this.kitchen.put();
                System.out.println("Neue Semmel geschmiert");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
