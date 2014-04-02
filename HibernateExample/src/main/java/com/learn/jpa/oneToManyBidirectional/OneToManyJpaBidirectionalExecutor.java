package com.learn.jpa.oneToManyBidirectional;

import com.learn.mappings.onetomany.oneToManyBidirectional.Account;
import com.learn.mappings.onetomany.oneToManyBidirectional.AccountUser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Set;


public class OneToManyJpaBidirectionalExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting the AccountUser and Account
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaDemo");
		EntityManager emOne = emf.createEntityManager();
		EntityTransaction txOne = emOne.getTransaction();
		txOne.begin();
		AccountUser user = new AccountUser();
		user.setName("Suresh Raina");
		
		Account account1 = new Account();
		account1.setAccountType("Savings Account");
		user.addAccounts(account1);
		
		Account account2 = new Account();
		account2.setAccountType("Salary Account");
		user.addAccounts(account2);
		
		emOne.persist(user);
		txOne.commit();
		emOne.close();
		
		//Retrieving the AccountUser and Account
		EntityManager emTwo = emf.createEntityManager();
		EntityTransaction txTwo = emTwo.getTransaction();
		txTwo.begin();
		AccountUser users = (AccountUser) emTwo.find(AccountUser.class, new Long(1));
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		System.out.println("AccountUser name is "+ users.getName());
		Set accounts = user.getAccounts();
		System.out.println("Number of accounts the use holds "+ accounts.size());
		for(Object account : accounts)
			System.out.println("Account Type is "+ ((Account)account).getAccountType());
	
		System.out.println("*********************************************");
		System.out.println("*********************************************");
		txTwo.commit();
		emTwo.close();
		
		emf.close();
		System.out.println("-------Done------");
	}
}
