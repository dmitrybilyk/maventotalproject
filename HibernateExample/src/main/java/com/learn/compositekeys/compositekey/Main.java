package com.learn.compositekeys.compositekey;

import com.learn.compositekeys.compositekey.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * AccountUser: Dmitry
 * Date: 26.05.13
 * Time: 9:04
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String args[]) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {

            transaction = session.beginTransaction();

            PurchaseOrder purchaseOrder = new PurchaseOrder();
            purchaseOrder.setItemName("Fruits");
            BillingAddress billingAddress = new BillingAddress("Matrosova", "Gorlovka");
            BillingAddress billingAddress2 = new BillingAddress("Matrosova2", "Gorlovka2");
            BillingAddress studentAddress = new BillingAddress("Matrosova2St", "Gorlovka2St");
//            purchaseOrder.setCity("Delhi");
//            purchaseOrder.setStreet("Rajouri Garden");
            purchaseOrder.setBillingAddress(billingAddress);
            PurchaseOrder purchaseOrder1 = new PurchaseOrder();
            purchaseOrder1.setItemName("Vegetables");
            purchaseOrder1.setBillingAddress(billingAddress2);


            Student student = new Student();
            student.setName("Dima");
            student.setBillingAddress(studentAddress);

            session.save(purchaseOrder);
            session.save(purchaseOrder1);
            session.save(student);

            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}