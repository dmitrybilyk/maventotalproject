package com.learn.inharitancestrategies.singletable;

import com.learn.inharitancestrategies.singletable.util.HibernateAnnotationUtil;
import org.hibernate.Session;

/**
 * Created by dmitry.bilyk on 3/25/14.
 */
public class Main {
    public static void main(String[] args) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleName("Car");

        TwoWheeler twoWheeler = new TwoWheeler();
        twoWheeler.setVehicleName("Bike");
        twoWheeler.setSteeringTwoWheeler("Bike Steering Handle");

        FourWheeler fourWheeler = new FourWheeler();
        fourWheeler.setVehicleName("Alto");
        fourWheeler.setSteeringFourWheeler("Alto Steering Wheel");

        session.save(vehicle);
        session.save(twoWheeler);
        session.save(fourWheeler);

        session.getTransaction().commit();
        session.close();
    }
}
