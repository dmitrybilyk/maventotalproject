package com.learn.queries.criteria;

import com.learn.queries.criteria.model.Person;
import com.learn.queries.hql.HqlTest;
import com.learn.queries.hql.model.Book;
import com.learn.queries.hql.model.Group;
import com.learn.queries.hql.model.Library;
import com.learn.queries.hql.model.Student;
import com.learn.queries.hql.util.HibernateAnnotationUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 02.03.13
 * Time: 20:04
 * To change this template use File | Settings | File Templates.
 */
public class CriteriaTest {
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
        Transaction transaction2 = null;
        try {


            transaction2 = session2.beginTransaction();

            Criteria criteria = session2.createCriteria(Person.class);
            List<Person> persons = criteria.list();


            for (Person person1 : persons) {
                System.out.println(person1.getAddress()+" criteria");
            }

            transaction2.commit();
        } catch (HibernateException e) {
            transaction2.rollback();
            e.printStackTrace();
        } finally {
            session2.close();
            HibernateAnnotationUtil.shutdown();
        }
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