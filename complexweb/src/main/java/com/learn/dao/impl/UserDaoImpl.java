package com.learn.dao.impl;

import com.learn.dao.api.UserDao;
import com.learn.model.cxfsoap.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.bilyk on 3/27/14.
 */
@Repository
public class UserDaoImpl implements UserDao {

    public void addUser(User user){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaWebPersistenceUnit");
        EntityManager emOne = emf.createEntityManager();
        EntityTransaction txOne = emOne.getTransaction();
        txOne.begin();
        emOne.persist(user);
        txOne.commit();
        emOne.close();
    }

    @Override
    public void printMessage() {
        System.out.println("userfsdfdsfdsfdsfsdfsd");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        users.add(new User("Dima"));
        users.add(new User("Vova"));
        users.add(new User("Nikita"));
        return users;
    }
}
