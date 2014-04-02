package com.learn.mappings.onetomany.oneToManyBidirectional;

import com.learn.mappings.onetomany.oneToManyBidirectional.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Set;

public class OneToManyAnnotationBidirectionalExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting the AccountUser and Account
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		AccountUser user = new AccountUser();
		user.setName("Suresh Raina");
		
		Account account1 = new Account();
		account1.setAccountType("Savings Account");
		user.addAccounts(account1);
		
		Account account2 = new Account();
		account2.setAccountType("Salary Account");
		user.addAccounts(account2);
		
		session.save(user);
		tx.commit();
		session.close(); 
		
		//Retrieving the AccountUser and Account
		Session sessionTwo = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txTwo = sessionTwo.beginTransaction();
		AccountUser users = (AccountUser) sessionTwo.load(AccountUser.class, new Long(1));
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		System.out.println("AccountUser name is "+ users.getName());
		Set accounts = user.getAccounts();
		System.out.println("Number of accounts the use holds " + accounts.size());
		for(Object account : accounts)
			System.out.println("Account Type is "+ ((Account)account).getAccountType());
	
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		txTwo.commit();
		sessionTwo.close();
		
		HibernateAnnotationUtil.shutdown();
		System.out.println("-------Done------");

	}

}
