package com.learn.hibernate.sql.joins_sql.model.fetchingstrategies;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class TestOneToMany {

    public static void main(String[] args) {

        System.out.println("Hibernate one to many (Annotation)");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Stock stock = new Stock();
        stock.setStockCode("7053");
        stock.setStockName("PADINIR");
        session.save(stock);

        StockDailyRecord stockDailyRecords = new StockDailyRecord();
        stockDailyRecords.setPriceOpen(new Float("1.2"));
        stockDailyRecords.setPriceClose(new Float("1.1"));
        stockDailyRecords.setPriceChange(new Float("10.0"));
        stockDailyRecords.setVolume(3000000L);
        stockDailyRecords.setDate((new Date()));

        stockDailyRecords.setStock(stock);
        stock.getStockDailyRecords().add(stockDailyRecords);

        session.save(stockDailyRecords);

        session.getTransaction().commit();
        System.out.println("Done");

    }

}
