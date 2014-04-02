package com.learn.mappings.onetomany.oneToManyUnidirectional;

import com.learn.mappings.onetomany.oneToManyUnidirectional.util.HibernateAnnotationUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OneToManyUniAnnotationExecutor {
	
	public static void main(String[] args) {
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		User user = new User();
		user.setName("Jacob Robinson");
        UserAddress userAddress = new UserAddress();
        userAddress.setStreet("Velikan");
        userAddress.setHouseNumber(33);
        userAddress.setFlatNumber(31);
        userAddress.setUser(user);

        User user2 = new User();
		user2.setName("Dmitry Bilyk");

        UserAddress userAddress2 = new UserAddress();
        userAddress2.setStreet("Matrosova");
        userAddress2.setHouseNumber(105);
        userAddress2.setFlatNumber(20);
        userAddress2.setUser(user2);

        User anonimousUser = new User();
        anonimousUser.setName("Anonimous AccountUser");

        Bank bank1 = new Bank("Ukrsow", "333334");
        Bank bank2 = new Bank("Pumb", "555334");

		Account account1 = new Account();
		account1.setAccountType("Savings Account");
		account1.setUser(user);
        account1.setAmount(4000);
        account1.setBank(bank1);
		Account account2 = new Account();
		account2.setAccountType("Salary Account");
		account2.setUser(user);
        account2.setAmount(34000);
        account2.setBank(bank2);
		Account account3 = new Account();
		account3.setAccountType("Credit Account");
		account3.setUser(user);
        account3.setAmount(14000);
        account3.setBank(bank1);
        Account account4 = new Account();
        account4.setAccountType("Credit Account");
//        account4.setUser(user2);
        account4.setAmount(500);
        account4.setBank(bank1);
        Account account5 = new Account();
        account5.setAccountType("Debit Account");
        account5.setUser(user2);
        account5.setAmount(3000);
        account5.setBank(bank1);

        Account anonimousAccount = new Account();
        anonimousAccount.setAccountType("Anonimous Account");

        session.save(user);
		session.save(account1);
		session.save(account2);
		session.save(account3);
        session.save(user2);
        session.save(userAddress);
        session.save(userAddress2);
        session.save(bank1);
        session.save(bank2);
        session.save(account4);
        session.save(account5);
        session.save(anonimousAccount);
        session.save(anonimousUser);
		tx.commit();
		session.close();

//        HibernateAnnotationUtil.shutdown();
        System.out.println("-----Done-----");

        //hql


        Session hqlSession = HibernateAnnotationUtil.getSessionFactory().openSession();
        Query query = hqlSession.createQuery("select distinct u from User u, Account a " +
                "join a.user");
        List<User> result = query.list();

        for (User resultUser : result) {
            System.out.println(resultUser.getName());
        }


        hqlSession.close();
//		HibernateAnnotationUtil.shutdown();
		System.out.println("-----HQL Done-----");
	}

}
