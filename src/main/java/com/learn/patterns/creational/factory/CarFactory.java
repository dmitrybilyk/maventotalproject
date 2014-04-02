package com.learn.patterns.creational.factory;
//Factory pattern is most suitable where there is some complex object creation
// steps are involved. To ensure that these steps are centralized and not
// exposed to composing classes, factory pattern should be used.
//CarFactory.java is our main class implemented using factory pattern. It instantiates a car instance only after determining its type.
public class CarFactory {
    public static Car buildCar(CarType model) {
        Car car = null;
        switch (model) {
            case SMALL:
                car = new SmallCar();
                break;

            case SEDAN:
                car = new SedanCar();
                break;

            case LUXURY:
                car = new LuxuryCar();
                break;

            default:
                // throw some exception
                break;
        }
        return car;
    }
}