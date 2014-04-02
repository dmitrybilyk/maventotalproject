package com.learn.queries.hql;

import com.learn.queries.hql.model.Book;
import com.learn.queries.hql.util.HibernateAnnotationUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 17.01.14
 * Time: 0:09
 * To change this template use File | Settings | File Templates.
 */
public class QueriesTrainings {
    static Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
    static long value = 2;
    public static void main(String[] args) {


//        Query query = session.createQuery("from Book where id = :code ");
        Query query = session.createQuery("update Book set name = :newName where id = :id ");
        query.setParameter("id", value);
        query.setParameter("newName", "NewSex");
        query.executeUpdate();
        printExisted();

//        In HQL, only the INSERT INTO … SELECT … is supported; there is no INSERT INTO …
// VALUES. HQL only support insert from another table. For example

        Query insertQuery = session.createQuery("insert into Book (id, name) select l.id + 11, l.name from Library l where l.id = :value");
        insertQuery.setParameter("value", value);
        insertQuery.executeUpdate();
        printExisted();
    }

    static void printExisted(){
        Query getQuery = session.createQuery("from Book");
//        getQuery.setParameter("id", value);
        List<Book> bookList = getQuery.list();
        for (Book book : bookList) {
            System.out.println(book.getName());
        }
    }
}
