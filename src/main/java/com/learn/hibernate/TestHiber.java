package com.learn.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 10:55
 * To change this template use File | Settings | File Templates.
 */
public class TestHiber {
    public static void main(String[] args){
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        User user = new User();
        user.setName("dima2");
        user.setId(1L);
        Set<Address> address = new HashSet<Address>();
        Address address1 = new Address();
        address1.setAddressName("addressName1");
        Address address2 = new Address();
        address2.setAddressName("addressName2");
        address.add(address1);
        address.add(address2);

        user.setUserAddress(address);

        User user2 = new User();
        user2.setUserAddress(address);
        user2.setName("user2");
        session.save(user);
        session.save(address1);
        session.save(address2);
//        session.save(user2);
        session.getTransaction().commit();
    }
}
