package com.learn.hibernate.criteria;

import com.learn.hibernate.HibernateUtil;
import com.learn.hibernate.sql.joins_sql.model.Person;
import org.hibernate.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 02.03.13
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
public class CriteriaTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            HibernateUtil.populatePersonDB(session);

            Criteria criteria = session.createCriteria(Person.class);
            List<Person> persons = criteria.list();


            for (Person person1 : persons) {
                System.out.println(person1.getAddress()+" criteria");
            }

            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


}