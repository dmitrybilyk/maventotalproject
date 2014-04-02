package com.learn.hibernate.model;

import com.learn.hibernate.HibernateUtil;
import com.learn.hibernate.model.sparetime.Address;
import com.learn.hibernate.model.sparetime.User;
import com.learn.hibernate.model.sparetime.dao.Dao;
import com.learn.hibernate.model.sparetime.dao.Impl.UserDaoImpl;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;

import java.util.Iterator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 30.05.12
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class HibernateTest {
    public static void main(String[] args){
//        AnnotationConfiguration conf = (new AnnotationConfiguration()).configure();
//        new SchemaExport(conf).create(true , false);
//        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
//        User user =  new User();
//        user.setLogin("Sa");
//        user.setPassword("description");
//        Session session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.save(user);
//        session.getTransaction().commit();
//        session.close();

//        testProjection();


        Dao dao = new UserDaoImpl();
//        dao.add200Entities();
//        dao.save();
//        User user2 = (User) session.get(User.class, 6);
//        System.out.print(user2.getUserName());
        printUsersNames(dao);
    }

    private static void testProjection() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Criteria criteria = session.createCriteria(User.class);
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("login"));
        projectionList.add(Projections.property("age"));
        projectionList.add(Projections.property("id"));
        projectionList.add(Projections.groupProperty("login"));

        criteria.setProjection(projectionList);
        List results = criteria.list();

        Iterator resultIterator = results.iterator();

        Object[] obj = (Object[]) resultIterator.next();
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i] + "\t");
        }
    }


    private static void printUsersNames(Dao dao) {
//        System.out.println(dao.averageAge());
//        System.out.println(double2);
        List<Address> addresses = dao.selectAddressWithUser();
        for (Address address : addresses) {
            System.out.println(address.getStreet());
        }
    }
}
