package com.learn.hibernate.sql.joins_sql;

import com.learn.hibernate.sql.joins_sql.model.Order;
import com.learn.hibernate.sql.joins_sql.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.07.12
 * Time: 13:21
 * To change this template use File | Settings | File Templates.
 */

//Theory
//SQL joins are used to query data from two or more tables, based on a relationship between certain columns in these tables.

//SQL JOIN
//
//        The JOIN keyword is used in an SQL statement to query data from two or more tables, based on a relationship between certain columns in these tables.
//
//        Tables in a database are often related to each other with keys.
//
//        A primary key is a column (or a combination of columns) with a unique value for each row.
//        Each primary key value must be unique within the table. The purpose is to bind data together, across tables,
//        without repeating all of the data in every table.

//JOIN: Return rows when there is at least one match in both tables
//        LEFT JOIN: Return all rows from the left table, even if there are no matches in the right table
//        RIGHT JOIN: Return all rows from the right table, even if there are no matches in the left table
//        FULL JOIN: Return rows when there is a match in one of the tables

//INNER JOIN is the same as JOIN.
//        The INNER JOIN keyword return rows when there is at least one match in both tables.
//
//        SELECT column_name(s)
//        FROM table_name1
//        INNER JOIN table_name2
//        ON table_name1.column_name=table_name2.column_name

//    LEFT JOIN
//The LEFT JOIN keyword returns all rows from the left table (table_name1), even if there are no matches in the right table (table_name2).

//SELECT column_name(s)
//        FROM table_name1
//        LEFT JOIN table_name2
//        ON table_name1.column_name=table_name2.column_name

//PS: In some databases LEFT JOIN is called LEFT OUTER JOIN.

//SQL RIGHT JOIN Keyword

//        The RIGHT JOIN keyword returns all the rows from the right table (table_name2), even if there are no matches in the left table (table_name1).

//        SQL RIGHT JOIN Syntax

//        SELECT column_name(s)
//        FROM table_name1
//        RIGHT JOIN table_name2
//        ON table_name1.column_name=table_name2.column_name

//SQL FULL JOIN Keyword

//        The FULL JOIN keyword return rows when there is a match in one of the tables.

//        SQL FULL JOIN Syntax

//        SELECT column_name(s)
//        FROM table_name1
//        FULL JOIN table_name2
//        ON table_name1.column_name=table_name2.column_name

//The SQL UNION operator combines two or more SELECT statements.
//        The SQL UNION Operator
//
//        The UNION operator is used to combine the result-set of two or more SELECT statements.
//
//        Notice that each SELECT statement within the UNION must have the same number of columns.
// The columns must also have similar data types. Also, the columns in each SELECT statement must be in the same order.
//        SQL UNION Syntax
//        SELECT column_name(s) FROM table_name1
//        UNION
//        SELECT column_name(s) FROM table_name2
//
//        Note: The UNION operator selects only distinct values by default. To allow duplicate values, use UNION ALL.
//        SQL UNION ALL Syntax
//        SELECT column_name(s) FROM table_name1
//        UNION ALL
//        SELECT column_name(s) FROM table_name2


//The GROUP BY Statement

//        The GROUP BY statement is used in conjunction with the aggregate functions to group the result-set by one or more columns.

//        SQL GROUP BY Syntax

//        SELECT column_name, aggregate_function(column_name)
//        FROM table_name
//        WHERE column_name operator value
//        GROUP BY column_name
//   example
//    select p_id, avg(orderprice) from orders group by p_id

//The HAVING Clause

//        The HAVING clause was added to SQL because the WHERE keyword could not be used with aggregate functions.

//        SQL HAVING Syntax

//        SELECT column_name, aggregate_function(column_name)
//        FROM table_name
//        WHERE column_name operator value
//        GROUP BY column_name
//        HAVING aggregate_function(column_name) operator value

//   example
//    select p_id, avg(orderprice) from orders group by p_id having avg(orderprice)<1000;

public class TestJoins {
    public static void main(String[] args){
        SessionFactory sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        Person person1 = new Person("Hansen", "Ola", "Timoteivn 10", "Sandnes");
        Person person2 = new Person("Svendson", "Tove", "Borgvn 23", "Sandnes");
        Person person3 = new Person("Pettersen", "Kari", "Storgt 20", "BE31ZZZ12345");
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(person1);
        session.save(person2);
        session.save(person3);

        Order order1 = new Order("77895", person3, new Date(), new Double("22.00"));
        Order order2 = new Order("44678", person3, new Date(), new Double("222.00"));
        Order order3 = new Order("22456", person1, new Date(), new Double("2322.00"));
        Order order4 = new Order("24562", person1, new Date(), new Double("12.00"));
        Order order5 = new Order("34764", null, new Date(), new Double("22.00"));
        session.save(order1);
        session.save(order2);
        session.save(order3);
        session.save(order4);
        session.save(order5);

        System.out.println(session.get(Person.class, new Integer(2)));

        session.getTransaction().commit();
        session.close();

//        Session session1 = sessionFactory.openSession();
//        session1.beginTransaction();
//
//        session1.getTransaction().commit();
    }
}
