package com.learn.cacheusing.secondlevel;

import com.learn.cacheusing.model.Person;
import com.learn.cacheusing.utils.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 18.08.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class TestSecondLevel {
    public static void main(String[] args){
        Session sessionToSavePerson = HibernateAnnotationUtil.getSessionFactory().openSession();
        Person personToSave = new Person("dima", "bilyk", "Matrosova", "Gorlovka");
        Transaction transaction = sessionToSavePerson.beginTransaction();
        sessionToSavePerson.persist(personToSave);
        transaction.commit();
        sessionToSavePerson.close();

        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();;
        Session session2 = HibernateAnnotationUtil.getSessionFactory().openSession();;
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
