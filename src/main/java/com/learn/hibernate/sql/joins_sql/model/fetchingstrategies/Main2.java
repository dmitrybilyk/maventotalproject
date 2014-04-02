package com.learn.hibernate.sql.joins_sql.model.fetchingstrategies;

import com.learn.hibernate.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */

//Theory
//Fetching Strategies

//        There are four fetching strategies

//        1. fetch-”join” = Disable the lazy loading, always load all the collections and entities.
//        2. fetch-”select” (default) = Lazy load all the collections and entities.
//        3. batch-size=”N” = Fetching up to ‘N’ collections or entities, *Not record*.
//        4. fetch-”subselect” = Group its collection into a sub select statement.

public class Main2 {

    public static void main(String[] args){
        Session session1  = HibernateUtil.getSessionFactory().openSession();
        Criteria criteria = session1.createCriteria(Stock.class);
        Stock stock = (Stock)session1.get(Stock.class, 1);
        Set<StockDailyRecord> sets = stock.getStockDailyRecords();

//call select from stock_daily_record
        for ( Iterator iter = sets.iterator();iter.hasNext(); ) {
            StockDailyRecord sdr = (StockDailyRecord) iter.next();
            System.out.println(sdr.getPriceChange());
            System.out.println(sdr.getDate());
        }
        List<Stock> stockList = criteria.list();
    }
}
