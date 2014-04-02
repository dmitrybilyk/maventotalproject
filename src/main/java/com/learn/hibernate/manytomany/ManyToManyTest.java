package com.learn.hibernate.manytomany;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 12:16
 * To change this template use File | Settings | File Templates.
 */
public class ManyToManyTest {
    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Set<Course> courses = new HashSet<Course>();
            Course maths = new Course("Maths");
            courses.add(maths);
            Course computerSience = new Course("Computer Science");
            courses.add(computerSience);

            Student student1 = new Student("Eswar", courses);
            Student student2 = new Student("Joe", courses);
            session.save(student1);
            session.save(student2);
            session.save(maths);
            session.save(computerSience);

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
