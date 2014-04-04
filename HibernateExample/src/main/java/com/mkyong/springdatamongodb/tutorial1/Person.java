package com.mkyong.springdatamongodb.tutorial1;

import java.io.Serializable;
import java.util.List;

/**
 * A simple POJO representing a Person
 */
public class Person implements Serializable {
 
 private static final long serialVersionUID = -5527566248002296042L;

//    we have to avoid nameing primary key as 'id' in spring data
 private String pid;
 private String firstName;
 private String lastName;
 private Double money;

private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public String getPid() {
  return pid;
 }
 
 public void setPid(String pid) {
  this.pid = pid;
 }
 
 public String getFirstName() {
  return firstName;
 }
 
 public void setFirstName(String firstName) {
  this.firstName = firstName;
 }
 
 public String getLastName() {
  return lastName;
 }
 
 public void setLastName(String lastName) {
  this.lastName = lastName;
 }
 
 public Double getMoney() {
  return money;
 }
 
 public void setMoney(Double money) {
  this.money = money;
 }
}