package com.learn.hibernate;

import com.learn.hibernate.sql.joins_sql.model.Order;
import com.learn.hibernate.sql.joins_sql.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void populatePersonDB(Session session) {
        Person person = new Person();
        person.setAddress("Matrosova");
        person.setCity("Gorlovka");
        person.setFirstName("Nikita");
        person.setLastName("Bilyk");
        session.save(person);
        Person person1 = new Person();
        person1.setAddress("Velikan");
        person1.setCity("Gorlovka");
        person1.setFirstName("Ruslan");
        person1.setLastName("Bilyk");
        session.save(person1);
        Person person2 = new Person();
        person2.setAddress("Isaevskaya-Matrosova");
        person2.setCity("Kharkov");
        person2.setFirstName("Dmitry");
        person2.setLastName("Bilyk");
        session.save(person2);
        Person person3 = new Person();
        person3.setAddress("50LetSSSR");
        person3.setCity("Kharkov");
        person3.setFirstName("Andrey");
        person3.setLastName("Gorbatov");
        session.save(person3);

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setOrderNo("177");
        order.setOrderPrice(3440.0);
        order.setPerson(person2);
        session.save(order);

        Order order1 = new Order();
        order1.setOrderDate(new Date());
        order1.setOrderNo("15");
        order1.setOrderPrice(30.0);
        order1.setPerson(person2);
        session.save(order1);

        Order order2 = new Order();
        order2.setOrderDate(new Date());
        order2.setOrderNo("61");
        order2.setOrderPrice(3033.0);
        order2.setPerson(person2);
        session.save(order2);

        Order order3 = new Order();
        order3.setOrderDate(new Date());
        order3.setOrderNo("1111");
        order3.setOrderPrice(3330.0);
        order3.setPerson(person3);
        session.save(order3);

        Order order4 = new Order();
        order4.setOrderDate(new Date());
        order4.setOrderNo("1");
        order4.setOrderPrice(430.0);
        order4.setPerson(person2);
        session.save(order4);

        Order order5 = new Order();
        order5.setOrderDate(new Date());
        order5.setOrderNo("111");
        order5.setOrderPrice(4430.0);
        order5.setPerson(person);
        session.save(order5);

        Order order6 = new Order();
        order6.setOrderDate(new Date());
        order6.setOrderNo("8");
        order6.setOrderPrice(130.0);
        order6.setPerson(person3);
        session.save(order6);

        Order order7 = new Order();
        order7.setOrderDate(new Date());
        order7.setOrderNo("14");
        order7.setOrderPrice(530.0);
        order7.setPerson(person3);
        session.save(order7);

        Order order8 = new Order();
        order8.setOrderDate(new Date());
        order8.setOrderNo("11");
        order8.setOrderPrice(3220.0);
        order8.setPerson(person2);
        session.save(order8);

    }
}
