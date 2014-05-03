package com.learn.spring.springinaction.methodsubstitution;

import com.learn.spring.springinaction.di.model.Match;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by dmitry on 03.05.14.
 */
public class Main {
    public static void main(String[] args) throws PerformanceException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/springinaction/methodsubstitution.xml");
        Magician magician = (Magician) ctx.getBean("harry");
        magician.perform();
        System.out.println(magician.getMagicWords());

        Performer createdNotInSpringContainerMagician = new Magician();
        Performer performer = (Magician) ctx.getBean("createdNotInSpringContainerMagician");
        performer.perform();

    }
}
