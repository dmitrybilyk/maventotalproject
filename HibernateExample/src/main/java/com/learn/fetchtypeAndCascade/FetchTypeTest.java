package com.learn.fetchtypeAndCascade;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 05.11.12
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class FetchTypeTest {
    public static void main(String[] args) {
        Session session = com.learn.fetchtypeAndCascade.HibernateAnnotationUtil.getSessionFactory().openSession();

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            populateDB(session);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        Session session2 = com.learn.fetchtypeAndCascade.HibernateAnnotationUtil.getSessionFactory().openSession();

        Transaction transaction2 = null;
        try {
            transaction2 = session2.beginTransaction();
            Query query = session2.createQuery("select l from com.learn.fetchtypeAndCascade.Library l");

            List<Library> list = (ArrayList<Library>)query.list();
            for (Library library : list) {
                System.out.println(library.getName());
                List<Book> books = library.getBooks();
                for (Book book : books) {
                    System.out.println(book.getName());
                }
            }

            session2.delete(list.get(0));
            transaction2.commit();
        } catch (HibernateException e) {
            transaction2.rollback();
            e.printStackTrace();
        } finally {
            session2.close();
        }

    }


    public static void populateDB(Session session){
        com.learn.fetchtypeAndCascade.Library schoolLibrary = new com.learn.fetchtypeAndCascade.Library("SchoolLibrary");
        com.learn.fetchtypeAndCascade.Library collegeLibrary = new com.learn.fetchtypeAndCascade.Library("CollegeLibrary");
        Book book1 = new Book("name1");
        Book book2 = new Book("name2");
        Book book3 = new Book("name3");
        List<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        schoolLibrary.setBooks(books);
        sessionSave(session, schoolLibrary, collegeLibrary, book1, book2, book3);

    }

    private static void sessionSave(Session session, Object... objects){
        for (Object object : objects) {
            if(object.getClass().getSimpleName().equals("Library")){
                com.learn.fetchtypeAndCascade.Library library = (com.learn.fetchtypeAndCascade.Library)object;
                session.save(library);
            }
            if(object.getClass().getSimpleName().equals("Book")){
                com.learn.fetchtypeAndCascade.Book book = (com.learn.fetchtypeAndCascade.Book)object;
                session.save(book);
            }
        }

    }
}
