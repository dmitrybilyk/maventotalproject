package com.learn.hibernate.sql.joins_sql.model.onetoone;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 25.07.12
 * Time: 21:26
 * To change this template use File | Settings | File Templates.
 */
public class testOneToOne {
         public static void main(String[] args) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Address address1 = new Address("OMR Road", "Chennai", "TN", "600097");
                Address address2 = new Address("Ring Road", "Banglore", "Karnataka", "560000");
                Student student1 = new Student("Eswar", address1);
                Student student2 = new Student("Joe", address2);
                session.save(student1);
                session.save(student2);
                session.save(address1);
                session.save(address2);
                student1.setStudentAddress(address1);
                student2.setStudentAddress(address2);
                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

        }
}
