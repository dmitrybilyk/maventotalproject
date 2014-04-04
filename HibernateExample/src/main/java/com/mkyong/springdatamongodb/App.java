//package com.mkyong.springdatamongodb;
//
//import java.util.List;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.data.mongodb.core.MongoOperations;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.mongodb.core.query.Update;
//
////import org.springframework.context.support.GenericXmlApplicationContext;
//
//public class App {
//
//    public static void main(String[] args) {
//
//	// For XML
//        //ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
//
//	// For Annotation
//	ApplicationContext ctx =
//             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
//	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
//
//	User user = new User("mkyong", "password123");
//
//	// save
//	mongoOperation.save(user);
//
//	// now user object got the created id.
//	System.out.println("1. user : " + user);
//
//	// query to search user
//	Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
//
//	// find the saved user again.
//	User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
//	System.out.println("2. find - savedUser : " + savedUser);
//
//	// update password
//	mongoOperation.updateFirst(searchUserQuery,
//                         Update.update("password", "new password"),User.class);
//
//	// find the updated user object
//	User updatedUser = mongoOperation.findOne(searchUserQuery, User.class);
//
//	System.out.println("3. updatedUser : " + updatedUser);
//
//
//        // List, it should be empty now.
//        List<User> listUser1 = mongoOperation.findAll(User.class);
//        System.out.println("2224. Number of user = " + listUser1.size());
//
////
////	// delete
////	mongoOperation.remove(searchUserQuery, User.class);
//
//	// List, it should be empty now.
//	List<User> listUser = mongoOperation.findAll(User.class);
//	System.out.println("4. Number of user = " + listUser.size());
//
//    }
//
//}