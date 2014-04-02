package com.learn.spring.springinjection.autowire.dao.api;

import com.learn.spring.springinjection.autowire.model.Vehicle;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:42
 * To change this template use File | Settings | File Templates.
 */
public interface DaoInterface {
    public void save(Vehicle vehicle);
    public Vehicle getByName(String name);
    public List<Vehicle> getAllVehicles();
}
