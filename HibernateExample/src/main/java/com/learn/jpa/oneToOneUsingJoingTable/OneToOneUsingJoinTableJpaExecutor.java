package com.learn.jpa.oneToOneUsingJoingTable;


import com.learn.mappings.onetoone.oneToOneUsingJoingTable.Passport;
import com.learn.mappings.onetoone.oneToOneUsingJoingTable.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class OneToOneUsingJoinTableJpaExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting Person and Passport
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaDemo");
		EntityManager emOne = emf.createEntityManager();
		EntityTransaction txOne = emOne.getTransaction();
		txOne.begin();
		
		Person person = new Person();
		person.setName("Sachin");
		Passport passport = new Passport();
		passport.setPassportNumber("B258789");
		//bidirectional
		person.setPassport(passport);
		passport.setPerson(person);		
		//This will persis person, and passport as we enabled casecade on person
		emOne.persist(person);
		txOne.commit();
		emOne.close();

		EntityManager emTwo = emf.createEntityManager();
		EntityTransaction txTwo = emTwo.getTransaction();
		txTwo.begin();
		Person person2 = new Person();
		person2.setName("Shewag");
		Passport pass = new Passport();
		pass.setPassportNumber("A123");
		//bidirectional
		person2.setPassport(pass);
		pass.setPerson(person2);	
		//This will persis person, and passport as we enabled casecade on person
		emTwo.persist(person2);
		txTwo.commit();
		emTwo.close();

		//Retrieving Person and Passport
		EntityManager emThree = emf.createEntityManager();
		EntityTransaction txThree = emThree.getTransaction();
		txThree.begin();
		Person validPerson = (Person) emThree.find(Person.class, new Long(2));
		Passport validPassport = validPerson.getPassport();
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("Person name is "+ validPerson.getName());
		System.out.println("Passport Number is "+ validPassport.getPassportNumber());
		System.out.println("********************************************");
		System.out.println("********************************************");
		txThree.commit();
		emThree.close();		

		emf.close();
		System.out.println("------Done------");
	}
}
