package com.learn.hibernate.sql.joins_sql.model.manytomany;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 11:56
 * To change this template use File | Settings | File Templates.
 */
public class TestManyToMany {
    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            Set<Course> courses = new HashSet<Course>();
            courses.add(new Course("Maths"));
            courses.add(new Course("Computer Science"));

            Student student1 = new Student("Eswar", courses);
            Student student2 = new Student("Joe", courses);
            session.save(student1);
            session.save(student2);

            transaction.commit();


            //for testing lazy and eager
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Student.class);
            List<Student> studentList = criteria.list();
            



        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }
}
