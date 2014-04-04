//package com.mkyong.springdatamongodb;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection = "users")
//public class User {
//
//	@Id
//    private String id;
//
//	String username;
//
//	String password;
//
//    public User() {
//    }
//
//    public User(String name, String password) {
//        this.username = name;
//        this.password = password;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}