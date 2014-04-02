package com.learn.saveorupdatetest;

import com.learn.saveorupdatetest.model.User;
import com.learn.saveorupdatetest.util.HibernateAnnotationUtil;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * Created with IntelliJ IDEA.
 * AccountUser: Dmitry
 * Date: 26.05.13
 * Time: 9:57
 * To change this template use File | Settings | File Templates.
 */
public class SaveOrUpdateMain {
    public static void main(String[] args) {
        Session session = HibernateAnnotationUtil.getSessionFactory().openSession();
        Transaction transaction = null;
//        try {
            transaction = session.beginTransaction();
//            saveOrUpdateObject(session);
//            transaction.commit();


            User user2 = new User("UniqueObject");

            //Here I want to cause NonUniqueObjectException.
            Session session2 = HibernateAnnotationUtil.getSessionFactory().openSession();
            Transaction beginTransaction = session2.beginTransaction();
            session2.persist(user2);
            beginTransaction.commit();
            session2.close();
            //user is detached from the session

            //change a value in this role
            user2.setName("UniqueException");

            //Open a new session
            session2 = HibernateAnnotationUtil.getSessionFactory().openSession();

            //Instanciation of the same role from the new session
            User userFromDB= (User) session2.get(User.class, user2.getId());

            //Now role and roleFromDB have the same id, but role is detached
            Transaction beginTransaction2 = session2.beginTransaction();

            //The next line will throw an exception
            try{
                //Save role and throw an exception
                session2.persist(user2);

                //This line will be never executed
                beginTransaction2.commit();
            }

            catch(NonUniqueObjectException nuoe){
                //Display this exception
                nuoe.printStackTrace();
            }

            session2.close();

//        }
//        catch (HibernateException e) {
//            transaction.rollback();
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
    }

    private static void saveOrUpdateObject(Session session) {

        User user = new User("userName");
        user.setId(1l);
        session.saveOrUpdate(user);
        Query query = session.createQuery("from User");
        User userFetched = (User) query.uniqueResult();
        userFetched.setName("new name");
        session.saveOrUpdate(userFetched);
    }
}
