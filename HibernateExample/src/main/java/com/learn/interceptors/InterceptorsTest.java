package com.learn.interceptors;

import org.hibernate.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 05.11.12
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class InterceptorsTest {
    public static void main(String[] args) {
        Session populateDBsession = HibernateAnnotationUtil.getSessionFactory().openSession();

        Transaction populateDBTransaction = null;
        try {
            populateDBTransaction = populateDBsession.beginTransaction();
            populateDB(populateDBsession);
            populateDBTransaction.commit();
        } catch (HibernateException e) {
            populateDBTransaction.rollback();
            e.printStackTrace();
        } finally {
            populateDBsession.close();
        }

        Session getLibraryFirstTimeSession = HibernateAnnotationUtil.getSessionFactory().openSession();

        Transaction transaction2 = null;
        try {
            Query query = getLibraryFirstTimeSession.createQuery("select l from Library l");

            Library library = (Library) query.uniqueResult();

            Library rezLibrary = library;
            rezLibrary.setName("ChangedLibrary");

            Session getLibrarySecondTimeSession = HibernateAnnotationUtil.getSessionFactory().openSession();
            transaction2 = getLibrarySecondTimeSession.beginTransaction();

            Query query2 = getLibrarySecondTimeSession.createQuery("select l from Library l");

            Library secondLibrary = (Library) query2.uniqueResult();

//            getLibrarySecondTimeSession.update(rezLibrary);
            getLibrarySecondTimeSession.delete(secondLibrary);

            transaction2.commit();
//            getLibraryFirstTimeSession.close();
//            Session.LockRequest lockRequest = getLibrarySecondTimeSession.buildLockRequest(new LockOptions(LockMode.OPTIMISTIC));
//            lockRequest.lock(secondLibrary);
            secondLibrary.setName("AfterLock");
        } catch (HibernateException e) {
            transaction2.rollback();
            e.printStackTrace();
        } finally {
            getLibraryFirstTimeSession.close();
        }

    }


    public static void populateDB(Session session){
        Library schoolLibrary = new Library("SchoolLibrary");
//        Library collegeLibrary = new Library("CollegeLibrary");
        Book book1 = new Book("name1");
        Book book2 = new Book("name2");
        Book book3 = new Book("name3");
        List<Book> books = new ArrayList<Book>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        schoolLibrary.setBooks(books);
        sessionSave(session, schoolLibrary, book1, book2, book3);

    }

    private static void sessionSave(Session session, Object... objects){
        for (Object object : objects) {
            if(object.getClass().getSimpleName().equals("Library")){
                Library library = (Library)object;
                session.save(library);
            }
            if(object.getClass().getSimpleName().equals("Book")){
                Book book = (Book)object;
                session.save(book);
            }
        }

    }
}
