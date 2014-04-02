package com.learn.hibernate.cacheusing.firstlevel;

import com.learn.hibernate.sql.joins_sql.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class TestFirstLevel {
    public static void main(String[] args){
    SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    Session session = sessionFactory.openSession();
    Session session2 = sessionFactory.openSession();
    session.beginTransaction();
    session2.beginTransaction();
    Person person = (Person) session.get(Person.class, 3L);
        person.setFirstName("test");
    Person person2 = (Person) session2.get(Person.class, 3L);
    System.out.println(person.getFirstName());
    System.out.println(person2.getFirstName());
//        session.save(user2);
    session.getTransaction().commit();
}
}
