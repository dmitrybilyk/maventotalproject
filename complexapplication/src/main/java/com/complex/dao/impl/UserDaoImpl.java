package com.complex.dao.impl;

import com.complex.dao.UserDao;
import com.complex.model.security.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.bilyk on 4/11/14.
 */

@Transactional
@Repository
public class UserDaoImpl implements UserDao {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<User> getAll() {

//        List<User> users = new ArrayList<User>();
//        users.add(new User("TestName", "TestLastName"));
//        users.add(new User("TestName2", "TestLastName2"));
//        users.add(new User("TestName3", "TestLastName3"));

        return sessionFactory.getCurrentSession().createQuery("from User").list();
//        return users;
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }

    @Override
    public User getUserById(int id) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where id = :id ");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public boolean deleteUser(int id) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete User where id = :id");
        query.setParameter("id", "id");
        int result = query.executeUpdate();
        return result > 0;
    }



    @Override
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().merge(user);
    }
}
