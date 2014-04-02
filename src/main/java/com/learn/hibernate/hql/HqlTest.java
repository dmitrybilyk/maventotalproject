package com.learn.hibernate.hql;

import com.learn.hibernate.hql.model.Group;
import com.learn.hibernate.hql.model.Library;
import com.learn.hibernate.hql.model.Student;
import com.learn.hibernate.hql.utils.HibernateUtil;
import com.learn.hibernate.sql.joins_sql.model.Person;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 05.11.12
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class HqlTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select s from Student as s inner join s.library as l");

        List list = query.list();
        Student student = (Student)list.iterator().next();

        System.out.println(student.getFirstName());


//        Transaction transaction = null;
//        try {
//            transaction = session.beginTransaction();
//            HibernateUtil.populateDB(session);
//            transaction.commit();
//        } catch (HibernateException e) {
//            transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }
}
