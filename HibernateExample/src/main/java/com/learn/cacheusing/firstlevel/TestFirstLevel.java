package com.learn.cacheusing.firstlevel;

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
public class TestFirstLevel {
    public static void main(String[] args) {
        Session sessionToSavePerson = HibernateAnnotationUtil.getSessionFactory().openSession();
        Person personToSave = new Person("dima", "bilyk", "Matrosova", "Gorlovka");
        Transaction transaction = sessionToSavePerson.beginTransaction();
        sessionToSavePerson.persist(personToSave);
        transaction.commit();
        sessionToSavePerson.close();

        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Person person = (Person) session.get(Person.class, 1);
        Person person2 = (Person) session.get(Person.class, 1);
        Person person3 = (Person) session.get(Person.class, 1);
        System.out.println(person.getFirstName());
}
}
