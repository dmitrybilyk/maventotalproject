package com.learn.spring.springinjection.autowire.dao.impl;

import com.learn.spring.springinjection.autowire.dao.api.DaoInterface;
import com.learn.spring.springinjection.autowire.model.Vehicle;
import com.learn.spring.springinjection.autowire.utils.VehicleBuilder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:44
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class JdbcDaoImpl implements DaoInterface {
    @Override
    public void save(Vehicle vehicle) {
        System.out.println("saving vehicle in jdbc");
    }

    @Override
    public Vehicle getByName(String name) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(name);
        System.out.println("get vehicle in jdbc");
        return vehicle;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(int i=0; i<5; i++){
            vehicles.add(VehicleBuilder.buildVehicle());
        }
        System.out.println("get all from jdbc");
        return vehicles;
    }
}
