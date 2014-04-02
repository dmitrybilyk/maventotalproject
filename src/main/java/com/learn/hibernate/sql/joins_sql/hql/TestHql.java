package com.learn.hibernate.sql.joins_sql.hql;

import com.learn.hibernate.sql.joins_sql.model.Order;
import com.learn.hibernate.sql.joins_sql.model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Date;
import java.util.List;

public class TestHql {
    public static void main(String[] args){
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        String city = "BE31ZZZ12345";
        String cutCity = city.substring(0, 4) + city.substring(7, city.length());
        String selectHql = "from Person p where REPLACE(p.city,SUBSTR(p.city, 6, 3),'') = :cutCity";
        //String selectHql = "SELECT SUBSTRING(p.city, 5, 3) from Person p";
        Query updateQuery = session.createQuery(selectHql);
        updateQuery.setString("cutCity", cutCity);
        List<Person> persons = updateQuery.list();

        for (Person person : persons) {
            System.out.println(person.getCity());
        }
        session.close();
    }
}
