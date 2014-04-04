package com.mkyong.springdatamongodb.tutorial1;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.document.mongodb.MongoOperations;
import org.springframework.data.document.mongodb.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.bilyk on 4/4/14.
 */
public class Main {
    protected static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
//        PersonService personService = new PersonService();
//        With spring data


        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("/mongodb/Spring-data.xml");
        MongoTemplate mongoTemplate = (MongoTemplate) ctx.getBean("mongoTemplate");

        InitService initService = new InitService();
        initService.setMongoTemplate(mongoTemplate);
        initService.init();

        PersonServiceSpringData personService = new PersonServiceSpringData();

        personService.setMongoTemplate(mongoTemplate);

//        List<Person> persons = personService.getAll();
//        for (Person person : persons) {
//            System.out.println(person.getFirstName());
//        }



        Group group = new Group();
        group.setName("groupName");
        Person person = new Person();
        person.setFirstName("Dima");
        person.setMoney(3d);
        List<Group> groups = new ArrayList<Group>();
        groups.add(group);
        person.setGroups(groups);

        personService.add(person);

        for(Group personGroup: personService.getByMoney(3d).getGroups()){
            System.out.println(personGroup.getName());
        }


    }
}
