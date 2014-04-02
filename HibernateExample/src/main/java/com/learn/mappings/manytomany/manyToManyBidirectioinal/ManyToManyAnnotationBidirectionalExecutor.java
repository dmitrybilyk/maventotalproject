package com.learn.mappings.manytomany.manyToManyBidirectioinal;

import com.learn.mappings.manytomany.manyToManyBidirectioinal.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class ManyToManyAnnotationBidirectionalExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting Student and Course objects
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
        com.learn.mappings.manytomany.manyToManyBidirectioinal.Student student1 = new com.learn.mappings.manytomany.manyToManyBidirectioinal.Student();
		student1.setStudentName("Prathap Kumar");

        com.learn.mappings.manytomany.manyToManyBidirectioinal.Student student2 = new com.learn.mappings.manytomany.manyToManyBidirectioinal.Student();
		student2.setStudentName("Joseph Raj Kumar");

		Course course1 = new Course();
		course1.setCourseName("English");

		Course course2 = new Course();
		course2.setCourseName("Computer");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course1);
		student2.addCourse(course2);

		session.save(student1);
		session.save(student2);
		tx.commit();
		session.close();

		//Loading the Student and the Course objects
		Session sessionTwo = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txTwo = sessionTwo.beginTransaction();
		List studentList = sessionTwo.createQuery(" from com.learn.mappings.manytomany.manyToManyBidirectioinal.Student ").list();
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		for(Object stud : studentList){
			System.out.println("Student Name is "+ ((Student)stud).getStudentName());
			Set courses = ((com.learn.mappings.manytomany.manyToManyBidirectioinal.Student)stud).getCourses();
			System.out.println("Courses to which this student is associated");
			for(Object course: courses)
				System.out.println(((Course)course).getCourseName());
		}
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		txTwo.commit();
		sessionTwo.close();

		HibernateAnnotationUtil.shutdown();
		System.out.println("------Done------");

	}

}
