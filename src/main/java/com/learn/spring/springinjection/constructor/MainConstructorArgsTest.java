package com.learn.spring.springinjection.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */
public class MainConstructorArgsTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("constructor-args-setter-context.xml");
//        Clothes clothes = (Clothes) applicationContext.getBean("adidas");
//        System.out.println(clothes);

//        com.learn.spring.springinjection.constructor.FootballPlayer player = (com.learn.spring.springinjection.constructor.FootballPlayer) applicationContext.getBean("footballPlayer");
//        System.out.println(player);
//        System.out.println(player.getAward().getName());
    }

}
