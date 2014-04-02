package com.learn.inharitancestrategies.joinedstrategy;

import com.learn.inharitancestrategies.tableperconcreteclass.Vehicle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity  
@Table(name="FOUR_WHEELER")  
public class FourWheeler extends Vehicle
{  
 @Column(name="STEERING_TYPE")  
 private String steeringFourWheeler;  
  
 public String getSteeringFourWheeler()   
 {  
  return steeringFourWheeler;  
 }  
  
 public void setSteeringFourWheeler(String steeringFourWheeler)   
 {  
  this.steeringFourWheeler = steeringFourWheeler;  
 }  
}  