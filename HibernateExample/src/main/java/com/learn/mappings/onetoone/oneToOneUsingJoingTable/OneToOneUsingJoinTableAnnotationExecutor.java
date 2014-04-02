package com.learn.mappings.onetoone.oneToOneUsingJoingTable;

import com.learn.mappings.onetoone.oneToOneUsingJoingTable.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class OneToOneUsingJoinTableAnnotationExecutor {

	public static void main(String[] args) {
		//Persisting Person and Passport
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Person person = new Person();
		person.setName("Sachin");
		Passport passport = new Passport();
		passport.setPassportNumber("B258789");
		//mappings.onetomany.bidirectional
		person.setPassport(passport);
		passport.setPerson(person);		
		//This will persist person, and passport as we enabled cascade on person
		session.save(person);		
		tx.commit();
		session.close();

		Session sessionX = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txX = sessionX.beginTransaction();
		Person person2 = new Person();
		person2.setName("Shewag");
		Passport pass = new Passport();
		pass.setPassportNumber("A123");
		//mappings.onetomany.bidirectional
		person2.setPassport(pass);
		pass.setPerson(person2);		
		//This will persis person, and passport as we enabled casecade on person
		sessionX.save(person2);		
		txX.commit();
		sessionX.close();

		//Retrieving Person and Passport
		Session sessionTwo =HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txTwo = sessionTwo.beginTransaction();
		Person validPerson = (Person) sessionTwo.load(Person.class, new Long(1));
		Passport validPassport = validPerson.getPassport();
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Person name is "+ validPerson.getName());
		System.out.println("Passport Number is "+ validPassport.getPassportNumber());
		System.out.println("********************************************");
		System.out.println("********************************************");
		txTwo.commit();
		sessionTwo.close();		

		HibernateAnnotationUtil.shutdown();

		System.out.println("------Done------");
	}
}
