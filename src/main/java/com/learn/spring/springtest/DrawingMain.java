package com.learn.spring.springtest;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.06.12
 * Time: 23:32
 * To change this template use File | Settings | File Templates.
 */
public class DrawingMain {
    public static void main(String[] args){
//        Triangle triangle = new Triangle();
//        triangle.draw();
//        BeanFactory beanFactory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
        AbstractApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[] {"spring.xml"});
        applicationContext.registerShutdownHook();
        Shape shape = (Shape)applicationContext.getBean("circle");
        shape.draw();
        System.out.println(applicationContext.getMessage("greeting", null, "defaultgreeting", null));

    }
}
