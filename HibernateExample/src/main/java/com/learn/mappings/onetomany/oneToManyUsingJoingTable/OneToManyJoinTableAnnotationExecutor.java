package com.learn.mappings.onetomany.oneToManyUsingJoingTable;

import com.learn.mappings.onetomany.oneToManyUsingJoingTable.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class OneToManyJoinTableAnnotationExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting the Buyer and the Property objects
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Property property = new Property();
		property.setPropertyName("1 Acre of Land");
		property.setPropertyValue("$10000");
		
		Buyer buyer = new Buyer();
		buyer.setBuyerName("Abraham");
		buyer.buyProperty(property);
		
		session.save(buyer);
		tx.commit();
		session.close();
		
		//Loading the Buyer and Property objects
		Session sessionTwo = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txTwo = sessionTwo.beginTransaction();
		
		Buyer buyers = (Buyer) sessionTwo.load(Buyer.class, new Long(1));
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
		sessionTwo.close();		
		
		HibernateAnnotationUtil.shutdown();
		System.out.println("--------Done--------");
	}

}
