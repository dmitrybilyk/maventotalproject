package com.learn.spring.springinjection.autowire.service.impl;

import com.learn.spring.springinjection.autowire.dao.api.DaoInterface;
import com.learn.spring.springinjection.autowire.service.api.VehicleService;
import com.learn.spring.springinjection.autowire.utils.VehicleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 8:32
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ForeignVehicleServiceImpl implements VehicleService {

    @Autowired
//    @Qualifier("hibernateDaoImpl")
//    private DaoInterface hibernateDaoImpl;
    private DaoInterface jdbcDaoImpl;
//    private DaoInterface jdbcDaoImpl;
//    private DaoInterface hibernateDaoImpl;

    @Override
    public void save() {
        jdbcDaoImpl.save(VehicleBuilder.buildVehicle());
    }

    @Override
    public void moving() {
        System.out.println("foreign moving");
    }

    @Override
    public void stopping() {
        System.out.println("foreign stopping");
    }

    @Override
    public void speeding() {
        System.out.println("foreign speeding");
    }
}
