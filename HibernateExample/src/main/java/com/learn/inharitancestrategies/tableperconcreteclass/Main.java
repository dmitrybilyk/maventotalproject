package com.learn.inharitancestrategies.tableperconcreteclass;

import com.learn.inharitancestrategies.tableperconcreteclass.util.HibernateAnnotationUtil;
import org.hibernate.Session;

/**
 * Created by dmitry.bilyk on 3/25/14.
 */
public class Main {
    public static void main(String[] args) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        Vehicle vehicle = new Vehicle();
//        vehicle.setVehicleName("Car");

        TwoWheeler twoWheeler = new TwoWheeler();
        twoWheeler.setVehicleName("Bike");
        twoWheeler.setSteeringTwoWheeler("Bike Steering Handle");

        TwoWheeler twoWheeler2 = new TwoWheeler();
        twoWheeler2.setVehicleName("Bike2");
        twoWheeler2.setSteeringTwoWheeler("Bike Steering Handle2");

        FourWheeler fourWheeler = new FourWheeler();
        fourWheeler.setVehicleName("Alto");
        fourWheeler.setSteeringFourWheeler("Alto Steering Wheel");

        FourWheeler fourWheeler2 = new FourWheeler();
        fourWheeler2.setVehicleName("Alto2");
        fourWheeler2.setSteeringFourWheeler("Alto Steering Wheel2");

//        session.save(vehicle);
        session.save(twoWheeler);
        session.save(twoWheeler2);
        session.save(fourWheeler);
        session.save(fourWheeler2);

        session.getTransaction().commit();
        session.close();
    }
}
