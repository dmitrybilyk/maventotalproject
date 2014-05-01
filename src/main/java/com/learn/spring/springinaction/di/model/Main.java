package com.learn.spring.springinaction.di.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 01.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/springinaction/match.xml");
        Match match = (Match) applicationContext.getBean("match");
        for(Team team: match.getTeams()){
            team.perfom();
        }
        Team real = (Team) applicationContext.getBean("real");
        Team real2 = (Team) applicationContext.getBean("real");
        System.out.println(real);
    }
}
