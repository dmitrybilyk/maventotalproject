package com.learn.spring.spel;

/**
 * Created by dmitry on 1/28/14.
 */
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SpringExpressionTest {
    public static void main(String... args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/spring.spel/expressionsTest.xml");
        ExpressionVO ex=(ExpressionVO)
                context.getBean("expression");
        int rn=ex.getRandomNumber();
        System.out.println(rn);
    }
}
