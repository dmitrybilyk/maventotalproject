package com.learn.jpa.oneToOneUsingPrimaryKey;

import com.learn.mappings.onetoone.oneToOneUsingPrimaryKey.Address;
import com.learn.mappings.onetoone.oneToOneUsingPrimaryKey.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class OneToOnePKJpaExecutor {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Start EntityManagerFactory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("helloworld");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		User user = new User();
		user.setName("Miru");
		Address address = new Address();
		address.setStreet("M.G. Road");
		address.setZipCode("402581");
		address.setCity("Bangalore");
		
		user.setAddress(address);
		address.setUser(user);
		//This will save the address object also as we enabled cascade to "save-update"
		em.persist(user);
		tx.commit();
		em.close();
		
		EntityManager emTwo = emf.createEntityManager();
		EntityTransaction txTwo = emTwo.getTransaction();
		txTwo.begin();
		User firstUser = (User) emTwo.find(User.class, new Long(1));
		Address userAddress = firstUser.getAddress();
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("AccountUser name "+ firstUser.getName());
		System.out.println("********** AccountUser UserAddress ********************");
		System.out.println("AccountUser Street Name "+ userAddress.getStreet());
		System.out.println("AccountUser ZipCode "+ userAddress.getZipCode());
		System.out.println("AccountUser City "+ userAddress.getCity());
		System.out.println("********************************************");
		System.out.println("********************************************");
		
		txTwo.commit();
		emTwo.close();
		emf.close();
		
		System.out.println("---------Done--------");
	}

}
