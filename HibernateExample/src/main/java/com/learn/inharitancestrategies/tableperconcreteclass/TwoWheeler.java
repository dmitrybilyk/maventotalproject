package com.learn.inharitancestrategies.tableperconcreteclass;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;  
  
@Entity  
@Table(name="TWO_WHEELER")  
public class TwoWheeler extends Vehicle   
{  
 @Column(name="STEERING_TYPE")  
 private String steeringTwoWheeler;  
  
 public String getSteeringTwoWheeler()  
 {  
  return steeringTwoWheeler;  
 }  
  
 public void setSteeringTwoWheeler(String steeringTwoWheeler)   
 {  
  this.steeringTwoWheeler = steeringTwoWheeler;  
 }  
}  