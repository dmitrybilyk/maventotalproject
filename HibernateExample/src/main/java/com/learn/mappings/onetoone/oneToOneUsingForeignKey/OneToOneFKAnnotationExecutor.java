package com.learn.mappings.onetoone.oneToOneUsingForeignKey;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.learn.mappings.onetoone.oneToOneUsingForeignKey.util.HibernateAnnotationUtil;

public class OneToOneFKAnnotationExecutor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Persisting the AccountUser and the Photo
		Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		PhotoUser user = new PhotoUser();
		user.setName("Mohan");
		Photo photo = new Photo();
		photo.setFileName("Image007.png");
		photo.setUser(user);
		user.setPhoto(photo);
		//This will save the user object also as we enabled cascade to "save-update" on photo
		session.save(photo);
		tx.commit();
		session.close();

		//Retrieving the AccountUser and the Photo
		Session sessionTwo = HibernateAnnotationUtil.getSessionFactory().openSession();
		Transaction txTwo = sessionTwo.beginTransaction();

		Photo photograph = (Photo)sessionTwo.load(Photo.class, new Long(1));
		PhotoUser newUser = photograph.getUser();
		System.out.println("********************************************");
		System.out.println("********************************************");
		System.out.println("AccountUser Id "+ newUser.getId());
		System.out.println("AccountUser Name "+ newUser.getName());
		System.out.println("Photo File Name "+ photograph.getFileName());
		System.out.println("********************************************");
		System.out.println("********************************************");

		txTwo.commit();
		sessionTwo.close();
		HibernateAnnotationUtil.shutdown();

		System.out.println("-------Done------");


        //deletion test

//        Session sessionForDeletion =HibernateAnnotationUtil.getSessionFactory().openSession();
//        Transaction tx3 = sessionForDeletion.beginTransaction();
//        AccountUser firstUserForDel = (AccountUser) sessionForDeletion.load(AccountUser.class, new Long(1));
//        sessionForDeletion.delete(firstUserForDel);
//        tx3.commit();

	}
}
