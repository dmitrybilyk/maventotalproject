package com.flash.dao.impl;

import com.flash.dao.HibernateUtil;
import com.flash.dao.api.UserDaoApi;
import com.flash.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 31.08.13
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserDaoHibernateImpl implements UserDaoApi {

    @Override
    public User getUserById(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
        User user = (User)session.get(User.class, userId);
        session.close();
        return user;
//        transaction.commit();
//        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        return (List<User>) query.list();
    }

    @Override
    public void updateUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUser(int userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(new User(userId));
        transaction.commit();
        session.close();
    }

    @Override
    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }
}
