package com.learn.cacheusing.querycache;

import com.learn.cacheusing.model.Person;
import com.learn.cacheusing.utils.HibernateAnnotationUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */

//Query cache - it's caching of id's

public class TestQueryCache {
    public static void main(String[] args){
        Session sessionToSavePerson = HibernateAnnotationUtil.getSessionFactory().openSession();
        Person personToSave = new Person("dima", "bilyk", "Matrosova", "Gorlovka");
        Transaction transaction = sessionToSavePerson.beginTransaction();
        sessionToSavePerson.persist(personToSave);
        transaction.commit();
        sessionToSavePerson.close();


        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Person person where person.id = 1");
        query.setCacheable(true);
        List personList = query.list();


        Session session2 = HibernateAnnotationUtil.getSessionFactory().openSession();
        Query query2 = session.createQuery("from Person person where person.id = 1");
        query2.setCacheable(true);
        List personList2 = query2.list();
        session.beginTransaction();
        session2.beginTransaction();
        Person person = (Person) session.get(Person.class, 1);
        person.setFirstName("test");
        Person person2 = (Person) session2.get(Person.class, 1);
        System.out.println(person.getFirstName());
        System.out.println(person2.getFirstName());
//        session.save(user2);
        session.getTransaction().commit();
    }
}
