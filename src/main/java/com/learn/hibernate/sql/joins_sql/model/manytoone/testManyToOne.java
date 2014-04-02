package com.learn.hibernate.sql.joins_sql.model.manytoone;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 25.07.12
 * Time: 20:41
 * To change this template use File | Settings | File Templates.
 */
public class testManyToOne {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Address address = new Address("OMR Road", "Chennai", "TN", "600097");
            //By using cascade=all option the address need not be saved explicitly when the student object is persisted the address will be automatically saved.
            //session.save(address);
            Student student1 = new Student("Eswar", address);
            Student student2 = new Student("Joe", address);
            session.save(student1);
            session.save(student2);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
