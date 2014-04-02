package com.learn.hibernate.sql.joins_sql.model.onetomany;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 25.07.12
 * Time: 21:02
 * To change this template use File | Settings | File Templates.
 */

//Theory
//According to the relationship a student can have any number
//of phone numbers.
//To create this relationship you need to have a STUDENT,
//PHONE and STUDENT_PHONE table.

//The @OneToMany annotation is used to create the one-to-many
// relationship between the Student and Phone entities.
// The @JoinTable annotation is used to create the
// STUDENT_PHONE link table and @JoinColumn annotation is
// used to refer the linking columns in both the tables.

public class testOneToMany {
        public static void main(String[] args) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Set<Phone> phoneNumbers = new HashSet<Phone>();
                phoneNumbers.add(new Phone("house","32354353"));
                phoneNumbers.add(new Phone("mobile","9889343423"));

                Student student = new Student("Eswar", phoneNumbers);
                session.save(student);

                transaction.commit();
            } catch (HibernateException e) {
                transaction.rollback();
                e.printStackTrace();
            } finally {
                session.close();
            }

        }
}
