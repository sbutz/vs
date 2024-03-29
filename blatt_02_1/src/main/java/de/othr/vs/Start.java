package de.othr.vs;

import de.othr.vs.KitchenCounter;
import de.othr.vs.Student;
import de.othr.vs.Waiter;

public class Start {

    public static void main(String[] args) {
        KitchenCounter theke = new KitchenCounter(4);

        new Thread( new Waiter(theke, "Kellner-1") ).start();
        new Thread( new Waiter(theke, "Kellner-2") ).start();

        for(int i = 1; i <= 8; i++)
            new Thread( new Student(theke, "Student-"+i) ).start();
    }
}
