package com.learn.mappings.onetoone.oneToOneUsingPrimaryKey;

import com.learn.mappings.onetoone.oneToOneUsingPrimaryKey.util.HibernateAnnotationUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OneToOnePKAnnotationExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		User user = new User();
		user.setName("Miru");
		Address address = new Address();
		address.setStreet("M.G. Road");
		address.setZipCode("402581");
		address.setCity("Bangalore");

		user.setAddress(address);
		address.setUser(user);
//        session.save(address);
		//This will save the address object also as we enabled cascade to "save-update"
		session.save(user);
		tx.commit();
		session.close();

		Session sessionTwo =HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txTwo = sessionTwo.beginTransaction();
		User firstUser = (User) sessionTwo.load(User.class, new Long(1));
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
		sessionTwo.close();
		HibernateAnnotationUtil.shutdown();

		System.out.println("---------Done--------");


        //I'm adding deletion here to test cascading

//        Session sessionForDeletion =HibernateAnnotationUtil.getSessionFactory().openSession();
//        Transaction tx3 = sessionForDeletion.beginTransaction();
//        AccountUser firstUserForDel = (AccountUser) sessionForDeletion.load(AccountUser.class, new Long(1));
//        sessionForDeletion.delete(firstUserForDel);
//        tx3.commit();

        Session sessionForDeletion =HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction tx3 = sessionForDeletion.beginTransaction();
        Address firstAddressForDel = (Address) sessionForDeletion.load(Address.class, new Long(1));
        sessionForDeletion.delete(firstAddressForDel);
        tx3.commit();
	}

}
