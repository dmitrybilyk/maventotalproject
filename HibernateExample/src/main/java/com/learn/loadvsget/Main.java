package com.learn.loadvsget;

import com.learn.loadvsget.util.HibernateAnnotationUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created with IntelliJ IDEA.
 * AccountUser: Dmitry
 * Date: 26.05.13
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {

//            saveObjects(session);

            transaction = session.beginTransaction();

            BillingAddress billingAddress = new BillingAddress("Matrosova", "Gorlovka");
            PurchaseOrder purchaseOrder = (PurchaseOrder) session.get(PurchaseOrder.class, billingAddress);
            PurchaseOrder purchaseOrder2 = (PurchaseOrder) session.load(PurchaseOrder.class, billingAddress);
            System.out.println(purchaseOrder);
//            System.out.println(purchaseOrder2);
            transaction.commit();
        }catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    private static void saveObjects(Session session) {
        Transaction transaction;
        transaction = session.beginTransaction();

        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setItemName("Fruits");
        BillingAddress billingAddress = new BillingAddress("Matrosova", "Gorlovka");
        BillingAddress billingAddress2 = new BillingAddress("Matrosova2", "Gorlovka2");
//            purchaseOrder.setCity("Delhi");
//            purchaseOrder.setStreet("Rajouri Garden");
        purchaseOrder.setBillingAddress(billingAddress);
        PurchaseOrder purchaseOrder1 = new PurchaseOrder();
        purchaseOrder1.setItemName("Vegetables");
        purchaseOrder1.setBillingAddress(billingAddress2);
        session.save(purchaseOrder);
        session.save(purchaseOrder1);
        transaction.commit();
    }
}
