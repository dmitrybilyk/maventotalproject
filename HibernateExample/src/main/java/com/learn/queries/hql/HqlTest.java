package com.learn.queries.hql;

import com.learn.queries.hql.model.Book;
import com.learn.queries.hql.model.Group;
import com.learn.queries.hql.model.Library;
import com.learn.queries.hql.model.Student;
import com.learn.queries.hql.util.HibernateAnnotationUtil;
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
public class HqlTest {
    public static void main(String[] args) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();

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

        Session session2 = HibernateAnnotationUtil.getSessionFactory().openSession();
        Query query = session2.createQuery("select s from com.learn.queries.hql.model.Student as s inner join s.library as l");

        List list = query.list();
        Iterator iterator = list.iterator();
        Student student = null;
        if (iterator.hasNext()) {
            student = (Student) list.iterator().next();
        }

        System.out.println(student.getFirstName());
        session2.close();


    }


    public static void populateDB(Session session){
        Library schoolLibrary = new Library("SchoolLibrary");
        Library collegeLibrary = new Library("CollegeLibrary");
        sessionSave(session, schoolLibrary, collegeLibrary);

        Student dimaStudent = new Student("Dima", "Bilyk");
        Student ruslanStudent = new Student("Ruslan", "Bilyk");
        Student vovaStudent = new Student("Vova", "Bobrov");
        Student iraStudent = new Student("Ira", "Tutina");
        Student nikitaStudent = new Student("Nikita", "Bilyk");
        sessionSave(session, dimaStudent, ruslanStudent, vovaStudent, iraStudent, nikitaStudent);

        Set<Library> dimasLibraries = new HashSet<Library>();
        dimasLibraries.add(collegeLibrary);
        dimaStudent.setLibrary(dimasLibraries);

        Set<Library> ruslansLibraries = new HashSet<Library>();
        ruslansLibraries.add(collegeLibrary);
        ruslanStudent.setLibrary(ruslansLibraries);

        Set<Library> nikitasLibraries = new HashSet<Library>();
        nikitasLibraries.add(schoolLibrary);
        nikitaStudent.setLibrary(nikitasLibraries);

        Book robinzonBook = new Book("Robinzon", new Date(), schoolLibrary);
        robinzonBook.setLibrary(schoolLibrary);
        Book pinBook = new Book("PrestupleniyeINakazaniye", new Date(), schoolLibrary);
        pinBook.setLibrary(schoolLibrary);
        Book mathBook = new Book("Maths", new Date(), schoolLibrary);
        mathBook.setLibrary(schoolLibrary);
        Book historyBook = new Book("History", new Date(), schoolLibrary);
        historyBook.setLibrary(schoolLibrary);
        Book sexBook = new Book("Sex", new Date(), null);
//        historyBook.setLibrary(schoolLibrary);
        sessionSave(session, robinzonBook, pinBook, mathBook, historyBook, sexBook);

        Book geometryBook = new Book("Geometry", new Date(), collegeLibrary);
        geometryBook.setLibrary(collegeLibrary);
        Book ukraineHistoryBook = new Book("UkraineHistory", new Date(), schoolLibrary);
        ukraineHistoryBook.setLibrary(collegeLibrary);
        Book economicsBook = new Book("Economics", new Date(), schoolLibrary);
        economicsBook.setLibrary(collegeLibrary);
        sessionSave(session, geometryBook, ukraineHistoryBook, economicsBook);

        List<Book> dimasBooks = new ArrayList<Book>();
        dimasBooks.add(historyBook);
        dimasBooks.add(mathBook);
        dimasBooks.add(pinBook);
        dimasBooks.add(pinBook);
        dimasBooks.add(pinBook);
        dimaStudent.setBooks(dimasBooks);

        List<Book> ruslansBooks = new ArrayList<Book>();
        ruslansBooks.add(geometryBook);
        ruslansBooks.add(geometryBook);
        ruslansBooks.add(economicsBook);
        ruslansBooks.add(economicsBook);
        ruslansBooks.add(ukraineHistoryBook);
        ruslanStudent.setBooks(ruslansBooks);

        List<Book> nikitasBooks = new ArrayList<Book>();
        nikitasBooks.add(mathBook);
        nikitasBooks.add(pinBook);
        nikitasBooks.add(pinBook);
        nikitaStudent.setBooks(nikitasBooks);

    }

    private static void sessionSave(Session session, Object... objects){
        for (Object object : objects) {
            if(object.getClass().getSimpleName().equals("Student")){
                Student student = (Student)object;
                session.save(student);
            }
            if(object.getClass().getSimpleName().equals("Group")){
                Group group = (Group)object;
                session.save(group);
            }
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
