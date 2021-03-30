package de.othr.vs;

class Car implements Comparable {

    private String licensePlate;

    Car(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    String getLicensePlate() {
        return this.licensePlate;
    }

    @Override
    public int compareTo(Object o) {
        Car c = (Car) o;
        return this.licensePlate.compareTo(c.getLicensePlate());
    }

}
