package com.learn.patterns.freemanAndFreeman.headfirst.observer.customobserver.interfacehandmade;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 7/19/14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("pattern.observer.custom/custom-observer-context.xml");
        MatchCenter matchCenter = (MatchCenter) applicationContext.getBean("matchCenter");
        Result result = (Result) applicationContext.getBean("result");

        Bookmackers bookmackers = (Bookmackers) applicationContext.getBean("bookmackers");
        matchCenter.registerObserver(bookmackers);

        Players players = (Players) applicationContext.getBean("players");
        matchCenter.registerObserver(players);
        matchCenter.setResult(result);

        result.setLocalGoals(3);
        matchCenter.setResult(result);

        matchCenter.unregisterObserver(bookmackers);
        matchCenter.setResult(result);
    }
}
