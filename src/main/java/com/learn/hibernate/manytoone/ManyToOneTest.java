package com.learn.hibernate.manytoone;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 20.06.12
 * Time: 12:05
 * To change this template use File | Settings | File Templates.
 */
public class ManyToOneTest {
    public static void main(String[] args){
        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction=session.beginTransaction();

        Boy boy=new Boy();
        boy.setBoyName("raj");

        Girl girl=new Girl();
        girl.setName("sita");
        girl.setBoy(boy);

        Girl girl2=new Girl();
        girl2.setName("Meena");
        girl2.setBoy(boy);

        session.save(girl);
        session.save(girl2);

        transaction.commit();

        session.close();
    }
    }

