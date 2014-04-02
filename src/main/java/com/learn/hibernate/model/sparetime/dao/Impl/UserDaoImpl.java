package com.learn.hibernate.model.sparetime.dao.Impl;

import com.learn.hibernate.HibernateUtil;
import com.learn.hibernate.model.sparetime.Address;
import com.learn.hibernate.model.sparetime.User;
import com.learn.hibernate.model.sparetime.dao.Dao;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.07.12
 * Time: 18:48
 * To change this template use File | Settings | File Templates.
 */
@Transactional
public class UserDaoImpl implements Dao {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    Session session = null;

    public void save() {
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user2 = new User("dimon77", "03564577");
        Address address = new Address("Gorlovka", "Matrosova", 105, 20);
        Address address2 = new Address("Harkov", "NovieDoma", 10, 12);
        address.setUser(user2);
        address2.setUser(user2);
        session.save(address);
        session.save(address2);
        session.save(user2);
        session.getTransaction().commit();
        session.close();
    }

    public void addUser(String login, String password, int i) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user2 = new User(login, password, i);
        session.save(user2);
        session.getTransaction().commit();
        session.close();
    }

    public List getEntityList() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List users = criteria.list();
        return users;
    }

    public List getEntityLikeName(String pattern) {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
//        .add(Restrictions.sqlRestriction("lower({alias}.login) like lower(?)", "%8", Hibernate.STRING));
        Property age = Property.forName("age");
        criteria.add(age.between(50, 60)).addOrder(age.desc()).setMaxResults(10);
        criteria.createCriteria("address").add(Restrictions.like("street", "Mat%"));
//        criteria.add(Restrictions.or(Restrictions.like("login", "lo%"), Restrictions.disjunction().add(Restrictions.eq("age", 50)).add(Restrictions.eq("age", 55))));
//        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List users = criteria.list();
        return users;
    }

    public void add200Entities() {
        for (int i = 0; i < 200; i++) {
            if(i==100){
                addUser("name"+i, "password"+i, 30-77+i);
            }else{
                addUser("name"+i, "password"+i, 30+i);
            }
        }
    }

    @Override
    public List getAddressOfUser(String s) {
        session = sessionFactory.openSession();
        Criteria addressCriteria = session.createCriteria(Address.class);
        addressCriteria.createCriteria("user").add(Restrictions.eq("age", 0));
        List addressList = addressCriteria.list();
        return addressList;
    }

    @Override
    public List getUsersByExample() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        User user = new User("name18");
        
        return criteria.add(Example.create(user)).list();
//        return criteria.list();
    }

    @Override
    public void getUsersByProjection() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> results = session.createCriteria(User.class)
                .setProjection(Projections.projectionList()
                        .add(Projections.avg("age"), "avage")
                )
                .addOrder( Order.desc("avage") )
                .list();

        Iterator iterator = results.iterator();


        while (iterator.hasNext()) {
            System.out.println("");
            Object[] objects = (Object[]) iterator.next();
            for (int i = 0; i < objects.length; i++) {
                System.out.print("|\t" + objects[i] + "\t");
            }
        }

    }

    @Override
    public int size() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);

        criteria.setProjection(Projections.avg("age"));

        return ((Integer) criteria.uniqueResult());

    }

    @Override
    public Double averageAge() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);

        criteria.setProjection(Projections.avg("age"));

        return ((Double) criteria.uniqueResult());

    }

    @Override
    public void groupByMethod() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);

        criteria.setProjection(Projections.groupProperty("age"));

        List results = criteria.list();

        Iterator resultIterator = results.iterator();

        Object[] obj = (Object[]) resultIterator.next();
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i] + "\t");
        }

    }

    @Override
    public List<User> testDetachedCriteria() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("age", 100));
        session = sessionFactory.openSession();
        List<User> users = (List<User>) detachedCriteria.getExecutableCriteria(session).list();
        return users;
    }

    @Override
    public List<Address> selectAddressWithUser() {
        session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Address.class);
        criteria.createCriteria("user").add(Restrictions.eq("id", 3L));
        return criteria.list();
    }

}
