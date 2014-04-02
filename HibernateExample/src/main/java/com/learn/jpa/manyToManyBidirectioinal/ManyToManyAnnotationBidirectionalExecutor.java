package com.learn.jpa.manyToManyBidirectioinal;

import com.learn.mappings.manytomany.manyToManyBidirectioinal.Course;
import com.learn.mappings.manytomany.manyToManyBidirectioinal.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class ManyToManyAnnotationBidirectionalExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting Student and Course objects
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaDemo");
		EntityManager emOne = emf.createEntityManager();
		EntityTransaction txOne = emOne.getTransaction();
		txOne.begin();
		
		Student student1 = new Student();
		student1.setStudentName("Prathap Kumar");

		Student student2 = new Student();
		student2.setStudentName("Joseph Raj Kumar");

		Course course1 = new Course();
		course1.setCourseName("English");

		Course course2 = new Course();
		course2.setCourseName("Computer");

		student1.addCourse(course1);
		student1.addCourse(course2);
		student2.addCourse(course1);
		student2.addCourse(course2);
		
		emOne.persist(student1);
		emOne.persist(student2);
		txOne.commit();
		emOne.close();

		//Loading the Student and the Course objects
		EntityManager emTwo = emf.createEntityManager();
		EntityTransaction txTwo = emTwo.getTransaction();
		txTwo.begin();
		List studentList = emTwo.createQuery(" from Student ").getResultList(); //as we use the Student class in different packages we mentioned the packeage name here
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		for(Object stud : studentList){
			System.out.println("Student Name is "+ ((com.learn.mappings.manytomany.manyToManyUnidirectional.Student)stud).getStudentName());
			Set courses = ((com.learn.mappings.manytomany.manyToManyUnidirectional.Student)stud).getCourses();
			System.out.println("Courses to which this student is associated");
			for(Object course: courses)
				System.out.println(((com.learn.mappings.manytomany.manyToManyUnidirectional.Course)course).getCourseName());
		}
		System.out.println("*****************************************");
		System.out.println("*****************************************");
		txTwo.commit();
		emTwo.close();

		emf.close();
		System.out.println("------Done------");
	}
}
