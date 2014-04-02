package com.learn.hibernate.hql.utils;

import com.learn.hibernate.hql.model.Book;
import com.learn.hibernate.hql.model.Group;
import com.learn.hibernate.hql.model.Library;
import com.learn.hibernate.hql.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.metamodel.source.annotations.EntityHierarchyBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 23.10.13
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */
public class HibernateUtil {
    private static final SessionFactory factory;
    private static ServiceRegistry serviceRegistry;
    static {

        try {
            Configuration configuration = new Configuration();
            configuration.configure("library_hql_hibernate.cfg.xml");
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            factory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
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
