package com.learn.spring.springinaction.di.model;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 01.05.14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/springinaction/match.xml");
        Player srnaAliasPlayer = (Player) applicationContext.getBean("srnaIdAlias");
//       System.out.println(srnaAliasPlayer.getName() + " from alias");
       System.out.println(srnaAliasPlayer.getName() + " from id alias");


        Match match = (Match) applicationContext.getBean("match");
        for(Team team: match.getTeams()){
            team.perfom();
        }
//        Team real = (Team) applicationContext.getBean("real");
//        Team real2 = (Team) applicationContext.getBean("real");
        Team shakhtarTeam = (Team) applicationContext.getBean("shakhtar");
        System.out.println("The name of the team is - " + shakhtarTeam.getName());
//        System.out.println(real);
    }
}
