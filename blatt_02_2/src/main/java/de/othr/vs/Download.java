package de.othr.vs;


import javax.swing.JProgressBar;

import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class Download implements Runnable {

    private final JProgressBar balken;

    // weitere Attribute zur Synchronisation hier definieren
    private CountDownLatch start, stop;

    public Download(JProgressBar balken, CountDownLatch start,
            CountDownLatch stop) {
        this.balken = balken;
        // ...
        this.start = start;
        this.stop = stop;
    }


    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */

    @Override
    public void run() {
        try {
            this.start.await();
            while (this.balken.getValue() < this.balken.getMaximum()) {
                Thread.sleep(new Random().nextInt(10) * 50);
                this.balken.setValue(this.balken.getValue() + 1);
            }
            this.stop.countDown();
        } catch(InterruptedException e) {
        }
    }
}
