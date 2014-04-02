package com.learn.hibernate.sql.joins_sql.model.onetomany2;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
public class Main2 {
    public static void main(String[] args){
        Session session1  = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session1.createCriteria(Stock.class);
        List<Stock> stockList = criteria.list();
    }
}
