package com.learn.jpa.oneToManyUsingJoingTable;

import com.learn.mappings.onetomany.oneToManyUsingJoingTable.Buyer;
import com.learn.mappings.onetomany.oneToManyUsingJoingTable.Property;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Set;

public class OneToManyJoinTableJpaExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting the Buyer and the Property objects
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaDemo");
		EntityManager emOne = emf.createEntityManager();
		EntityTransaction txOne = emOne.getTransaction();
		txOne.begin();
		
		Property property = new Property();
		property.setPropertyName("1 Acre of Land");
		property.setPropertyValue("$10000");
		
		Buyer buyer = new Buyer();
		buyer.setBuyerName("Abraham");
		buyer.buyProperty(property);
		
		emOne.persist(buyer);
		txOne.commit();
		emOne.close();
		
		//Loading the Buyer and Property objects
		EntityManager emTwo = emf.createEntityManager();
		EntityTransaction txTwo = emTwo.getTransaction();
		txTwo.begin();
		
		Buyer buyers = (Buyer) emTwo.find(Buyer.class, new Long(1));
		System.out.println("******************************************");
		System.out.println("******************************************");
		System.out.println("Buyer name "+ buyers.getBuyerName());
		Set properties = buyers.getProperties();
		for(Object props : properties){
			System.out.println("Property Name "+ ((Property)props).getPropertyName());
			System.out.println("Property Value "+ ((Property)props).getPropertyValue());
		}		
		System.out.println("******************************************");
		System.out.println("******************************************");
		txTwo.commit();
		emTwo.close();		
		
		emf.close();
		System.out.println("--------Done--------");
	}
}
